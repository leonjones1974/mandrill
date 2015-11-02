package uk.camsw.mandrill;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public class QuoteService {
    public static final Price BID_MARGIN = new Price("-0.02");
    public static final Price ASK_MARGIN = new Price("0.02");

    private final LiveOrderBoard orderBoard;

    public QuoteService(LiveOrderBoard orderBoard) {
        this.orderBoard = checkNotNull(orderBoard);
    }

    /**
     * Could have opted to use the java8 streams stuff here, but felt it added complexity in this particular case
     * both in terms of readability and the ability to reason about the time complexity of the algorithm
     */
    public Optional<Quote> createQuote(QuoteRequest request) {
        checkNotNull(request);

        Price highestBid = null;
        Price lowestAsk = null;

        for (Order order : orderBoard.findOrders(request.getPaymentCurrency())) {
            if (request.matchesOrder(order)) {
                Price orderPrice = order.getPrice();
                if (order.isBuy()) {
                    highestBid = Price.max(highestBid, orderPrice);
                } else if (order.isSell()) {
                    lowestAsk = Price.min(lowestAsk, orderPrice);
                }
            }
        }
        return highestBid != null && lowestAsk != null
                ? Optional.of(new Quote(highestBid.add(BID_MARGIN), lowestAsk.add(ASK_MARGIN)))
                : Optional.<Quote>empty();
    }
}
