package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.camsw.mandrill.Ccy.*;
import static uk.camsw.mandrill.Direction.BUY;
import static uk.camsw.mandrill.Direction.SELL;

public class OrderTest {
    private static final Size SIZE = new Size(500);

    @Test
    public void create_whereValid_ShouldContainSize() {
        Order order = new Order(SIZE, EUR, BUY);
        assertThat(order.getSize()).isEqualTo(SIZE);
    }

    @Test
    public void create_whereValid_ShouldContainPaymentCurrency() {
        Order order = new Order(SIZE, EUR, BUY);
        assertThat(order.getPaymentCurrency()).isEqualTo(EUR);
    }

    @Test
    public void create_whereValid_ShouldDirection() {
        Order order = new Order(SIZE, EUR, BUY);
        assertThat(order.getDirection()).isEqualTo(BUY);
    }

    @Test(expected = NullPointerException.class)
    public void create_wherePaymentCurrencyIsNull_ShouldRaiseError() {
        new Order(SIZE, null, Direction.SELL);
    }

    @Test(expected = NullPointerException.class)
    public void create_whereDirectionIsNull_ShouldRaiseError() {
        new Order(SIZE, EUR, null);
    }

    @Test(expected = NullPointerException.class)
    public void create_whereSizeIsNull_ShouldRaiseError() {
        new Order(null, GBP, SELL);
    }

}