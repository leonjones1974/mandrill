package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.camsw.mandrill.Ccy.*;
import static uk.camsw.mandrill.Direction.BUY;
import static uk.camsw.mandrill.Direction.SELL;

public class OrderTest {
    private static final Size SIZE = new Size(500);
    private static final Price PRICE = new Price("123.23");

    @Test
    public void create_whereValid_ShouldContainSize() {
        Order order = new Order(SIZE, EUR, BUY, PRICE);
        assertThat(order.getSize()).isEqualTo(SIZE);
    }

    @Test
    public void create_whereValid_ShouldContainPaymentCurrency() {
        Order order = new Order(SIZE, EUR, BUY, PRICE);
        assertThat(order.getPaymentCurrency()).isEqualTo(EUR);
    }

    @Test
    public void create_whereValid_ShouldContainDirection() {
        Order order = new Order(SIZE, EUR, BUY, PRICE);
        assertThat(order.getDirection()).isEqualTo(BUY);
    }

    @Test
    public void create_whereValid_ShouldContainPrice() {
        Order order = new Order(SIZE, EUR, BUY, PRICE);
        assertThat(order.getPrice()).isEqualTo(PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void create_wherePaymentCurrencyIsNull_ShouldRaiseError() {
        new Order(SIZE, null, Direction.SELL, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void create_whereDirectionIsNull_ShouldRaiseError() {
        new Order(SIZE, EUR, null, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void create_whereSizeIsNull_ShouldRaiseError() {
        new Order(null, GBP, SELL, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void create_wherePriceIsNull_ShouldRaiseError() {
        new Order(SIZE, GBP, SELL, null);
    }


}