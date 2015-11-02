package uk.camsw.mandrill;

import static com.google.common.base.Preconditions.checkNotNull;

public class Order {
    private final Size size;
    private final Ccy paymentCurrency;
    private final Direction direction;
    private final Price price;

    public Order(Size size, Ccy paymentCurrency, Direction direction, Price price) {
        this.size = checkNotNull(size);
        this.paymentCurrency = checkNotNull(paymentCurrency);
        this.direction = checkNotNull(direction);
        this.price = checkNotNull(price);
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

    public Price getPrice() {
        return price;
    }
}
