package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.camsw.mandrill.Ccy.*;

public class QuoteRequestTest {

    private static final Size SIZE = new Size(500);
    public static final Price PRICE = new Price("100");

    @Test
    public void create_WhereValid_ShouldContainSize() {
        QuoteRequest quoteRequest = new QuoteRequest(SIZE, EUR);
        assertThat(quoteRequest.getSize()).isEqualTo(SIZE);
    }

    @Test
    public void create_WhereValid_ShouldContainPaymentCurrency() {
        QuoteRequest quoteRequest = new QuoteRequest(SIZE, USD);
        assertThat(quoteRequest.getPaymentCurrency()).isEqualTo(USD);
    }

    @Test(expected = NullPointerException.class)
    public void create_WherePaymentCurrencyIsNull_ShouldRaiseError() {
        new QuoteRequest(SIZE, null);
    }

    @Test(expected = NullPointerException.class)
    public void create_WhereSizeIsNull_ShouldRaiseError() {
        new QuoteRequest(null, GBP);
    }

    @Test
    public void matchesOrder_WhereSizeAndCurrencyAreEqual_ShouldReturnTrue() {
        QuoteRequest request = new QuoteRequest(SIZE, EUR);
        Order order = Order.createBuy(SIZE, EUR, PRICE);
        assertThat(request.matchesOrder(order)).isTrue();
    }

    @Test
    public void matches_WhereSizesDiffer_ShouldReturnFalse() {
        QuoteRequest request = new QuoteRequest(SIZE, EUR);
        Order order = Order.createBuy(new Size(SIZE.intValue() + 1000), EUR, PRICE);
        assertThat(request.matchesOrder(order)).isFalse();
    }

    @Test
    public void matches_WhereCurrenciesDiffer_ShouldReturnFalse() {
        QuoteRequest request = new QuoteRequest(SIZE, USD);
        Order order = Order.createBuy(SIZE, EUR, PRICE);
        assertThat(request.matchesOrder(order)).isFalse();
    }
}