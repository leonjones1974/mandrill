package uk.camsw.mandrill;

import static com.google.common.base.Preconditions.checkNotNull;

public class QuoteRequest {

    private final Size size;
    private Ccy paymentCurrency;

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
}
