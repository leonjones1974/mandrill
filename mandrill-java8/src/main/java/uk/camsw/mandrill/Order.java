package uk.camsw.mandrill;

import com.google.common.base.MoreObjects;

import static com.google.common.base.Preconditions.checkNotNull;

public class Order {
    private final Size size;
    private final Ccy paymentCurrency;
    private final Direction direction;
    private final Price price;

    public static Order createSell(Size size, Ccy paymentCurrency, Price price) {
        return new Order(size, paymentCurrency, Direction.SELL, price);
    }

    public static Order createBuy(Size size, Ccy paymentCurrency, Price price) {
        return new Order(size, paymentCurrency, Direction.BUY, price);
    }

    private Order(Size size, Ccy paymentCurrency, Direction direction, Price price) {
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

    public Price getPrice() {
        return price;
    }

    public boolean isSell() {
        return direction == Direction.SELL;
    }

    public boolean isBuy() {
        return direction == Direction.BUY;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("size", size)
                .add("paymentCurrency", paymentCurrency)
                .add("direction", direction)
                .add("price", price)
                .toString();
    }


    private enum Direction {
        BUY,
        SELL
    }
}