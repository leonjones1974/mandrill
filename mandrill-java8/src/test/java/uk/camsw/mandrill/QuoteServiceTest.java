package uk.camsw.mandrill;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuoteServiceTest {


    @Mock
    private LiveOrderBoard mockLiveOrderBoard;
    private List<Order> usdOrders = new ArrayList<>();
    private List<Order> eurOrders = new ArrayList<>();
    private List<Order> gbpOrders = new ArrayList<>();
    private QuoteService quoteService;

    @Before
    public void before() {
        when(mockLiveOrderBoard.findOrders(Ccy.USD)).thenReturn(usdOrders);
        when(mockLiveOrderBoard.findOrders(Ccy.EUR)).thenReturn(eurOrders);
        when(mockLiveOrderBoard.findOrders(Ccy.GBP)).thenReturn(gbpOrders);
        quoteService = new QuoteService(mockLiveOrderBoard);
    }

    @Test(expected = NullPointerException.class)
    public void create_WhereLiveOrderBoardIsNull_ShouldRaiseError() {
        new QuoteService(null);
    }

    @Test(expected = NullPointerException.class)
    public void createQuote_WhereRequestIsNull_ShouldRaiseError() {
        quoteService.createQuote(null);
    }

    @Test
    public void createQuote_WhereNoLiveOrders_ShouldReturnEmptyOption() {
        assertThat(quoteService.createQuote(usdRequest(100))).isEmpty();
    }

    @Test
    public void createQuote_WhereOnlyMatchingBuyOrder_ShouldReturnEmptyOption() {
        usdOrders.add(usdBuyOrder(100, "123.50"));
        assertThat(quoteService.createQuote(usdRequest(100))).isEmpty();
    }

    @Test
    public void createQuote_WhereOnlyMatchingSellOrder_ShouldReturnEmptyOption() {
        usdOrders.add(usdSellOrder(100, "124.50"));
        assertThat(quoteService.createQuote(usdRequest(100))).isEmpty();
    }

    @Test
    public void createQuote_WhereSingleMatchOnEachSide_ShouldReturnQuoteWithCorrectMarginsApplied() {
        usdOrders.add(usdBuyOrder(100, "100.00"));
        usdOrders.add(usdSellOrder(100, "200.00"));

        Optional<Quote> quote = quoteService.createQuote(usdRequest(100));
        assertThat(quote)
                .isPresent()
                .contains(new Quote(new Price("99.98"), new Price("200.02")));
    }

    @Test
    public void createQuote_WhereMultipleMatchesOnEachSide_ShouldReturnQuoteBasedOnHighestBidAndLowestSell() {
        usdOrders.add(usdBuyOrder(100, "123.50"));
        usdOrders.add(usdBuyOrder(100, "125.50"));
        usdOrders.add(usdSellOrder(100, "124.50"));
        usdOrders.add(usdSellOrder(100, "122.50"));

        assertThat(quoteService.createQuote(usdRequest(100)))
                .isPresent()
                .contains(new Quote(new Price("125.48"), new Price("122.52")));
    }

    @Test
    public void createQuote_ShouldOnlyMatchOrdersOfSameCurrency() {
        usdOrders.add(usdBuyOrder(100, "123.50"));
        usdOrders.add(usdSellOrder(100, "124.50"));
        eurOrders.add(eurBuyOrder(100, "123.50"));
        eurOrders.add(eurSellOrder(100, "124.50"));

        assertThat(quoteService.createQuote(gbpRequest(100))).isEmpty();
    }

    /**
     * Very edge-casey this one, the sort of thing I'd want to discuss while pairing.  Because I've opted for the
     * general approach of placing business logic within the domain, it follows that the implementation of matches
     * should also check on currency
     * <p>
     * Some might choose to mock the order model here, I prefer not to mock domain objects unless they depend on
     * lots of collaborators in a rich domain impl (i.e.  .save())
     */
    @Test
    public void createQuote_ShouldNotAssumeTheLiveOrderBoardHasReturnedOrdersOfTheCorrectCurrencyOnly() {
        // Here, we get a couple of matching euro orders, even though we requested usd
        usdOrders.add(eurBuyOrder(100, "123.50"));
        usdOrders.add(eurSellOrder(100, "124.50"));

        assertThat(quoteService.createQuote(usdRequest(100))).isEmpty();
    }

    @Test
    public void workedExample() {
        usdOrders.add(usdBuyOrder(200, "232.71"));
        usdOrders.add(usdSellOrder(100, "232.74"));
        usdOrders.add(usdSellOrder(200, "232.73"));
        usdOrders.add(usdBuyOrder(500, "232.71"));
        usdOrders.add(usdBuyOrder(100, "232.70"));
        usdOrders.add(usdSellOrder(200, "232.75"));
        usdOrders.add(usdBuyOrder(500, "232.69"));
        usdOrders.add(usdSellOrder(300, "232.76"));
        usdOrders.add(usdBuyOrder(200, "232.70"));

        assertThat(quoteService.createQuote(usdRequest(200)))
                .contains(quote("232.69", "232.75"));

        assertThat(quoteService.createQuote(usdRequest(100)))
                .contains(quote("232.68", "232.76"));

    }

    private static QuoteRequest usdRequest(int size) {
        return new QuoteRequest(new Size(size), Ccy.USD);
    }

    private static QuoteRequest gbpRequest(int size) {
        return new QuoteRequest(new Size(size), Ccy.GBP);
    }

    private static Order usdBuyOrder(int size, String price) {
        return Order.createBuy(new Size(size), Ccy.USD, new Price(price));
    }

    private static Order usdSellOrder(int size, String price) {
        return Order.createSell(new Size(size), Ccy.USD, new Price(price));
    }

    private static Order eurBuyOrder(int size, String price) {
        return Order.createBuy(new Size(size), Ccy.EUR, new Price(price));
    }

    private static Order eurSellOrder(int size, String price) {
        return Order.createSell(new Size(size), Ccy.EUR, new Price(price));
    }

    private static Quote quote(String bidPrice, String askPrice) {
        return new Quote(new Price(bidPrice), new Price(askPrice));
    }
}