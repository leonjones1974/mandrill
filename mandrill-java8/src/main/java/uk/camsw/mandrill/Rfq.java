package uk.camsw.mandrill;

import static com.google.common.base.Preconditions.checkNotNull;

public class Rfq {

    private final Size size;
    private Ccy paymentCurrency;

    public Rfq(Size size, Ccy paymentCurrency) {
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
