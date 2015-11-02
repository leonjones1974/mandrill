package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.camsw.mandrill.Ccy.EUR;
import static uk.camsw.mandrill.Ccy.GBP;

public class OrderTest {
    private static final Size SIZE = new Size(500);
    private static final Price PRICE = new Price("123.23");

    @Test
    public void create_WhereValid_ShouldContainSize() {
        Order order = Order.createBuy(SIZE, EUR, PRICE);
        assertThat(order.getSize()).isEqualTo(SIZE);
    }

    @Test
    public void create_WhereValid_ShouldContainPaymentCurrency() {
        Order order = Order.createBuy(SIZE, EUR, PRICE);
        assertThat(order.getPaymentCurrency()).isEqualTo(EUR);
    }

    @Test
    public void create_ForBuy_ShouldIndicateCorrectDirection() {
        Order order = Order.createBuy(SIZE, EUR, PRICE);
        assertThat(order)
                .matches(Order::isBuy)
                .matches(o -> !o.isSell());
    }

    @Test
    public void create_ForSell_ShouldIndicateCorrectDirection() {
        Order order = Order.createSell(SIZE, EUR, PRICE);
        assertThat(order)
                .matches(Order::isSell)
                .matches(o -> !o.isBuy());
    }

    @Test
    public void create_WhereValid_ShouldContainPrice() {
        Order order = Order.createBuy(SIZE, EUR, PRICE);
        assertThat(order.getPrice()).isEqualTo(PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void create_WherePaymentCurrencyIsNull_ShouldRaiseError() {
        Order.createBuy(SIZE, null, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void create_WhereSizeIsNull_ShouldRaiseError() {
        Order.createSell(null, GBP, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void create_WherePriceIsNull_ShouldRaiseError() {
        Order.createSell(SIZE, GBP, null);
    }

}