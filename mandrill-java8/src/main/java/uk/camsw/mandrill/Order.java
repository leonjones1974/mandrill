package uk.camsw.mandrill;

import static com.google.common.base.Preconditions.checkNotNull;

public class Order {
    private final Size size;
    private final Ccy paymentCurrency;
    private final Direction direction;

    public Order(Size size, Ccy paymentCurrency, Direction direction) {
        this.size = checkNotNull(size);
        this.paymentCurrency = checkNotNull(paymentCurrency);
        this.direction = checkNotNull(direction);
    }

    public Size getSize() {
        return size;
    }

    public Ccy getPaymentCurrency() {
        return paymentCurrency;
    }

    public Direction getDirection() {
        return direction;
    }
}
