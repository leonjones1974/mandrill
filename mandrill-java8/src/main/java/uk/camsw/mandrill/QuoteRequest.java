package uk.camsw.mandrill;

import com.google.common.base.MoreObjects;

import static com.google.common.base.Preconditions.checkNotNull;

public class QuoteRequest {

    private final Size size;
    private final Ccy paymentCurrency;

    public QuoteRequest(Size size, Ccy paymentCurrency) {
        this.size = checkNotNull(size);
        this.paymentCurrency = checkNotNull(paymentCurrency);
    }

    public Ccy getPaymentCurrency() {
        return paymentCurrency;
    }

    public Size getSize() {
        return size;
    }

    /**
     * Whether you'd really want to encapsulate this logic here or elsewhere here depends on how the domain is to
     * be structured.  I think it's fine, given the available information
     */
    public boolean matchesOrder(Order order) {
        return order != null
                && size.equals(order.getSize())
                && paymentCurrency == order.getPaymentCurrency();
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("size", size)
                .add("paymentCurrency", paymentCurrency)
                .toString();
    }
}
