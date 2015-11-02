package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.camsw.mandrill.Ccy.*;

public class RfqTest {

    private static final Size SIZE = new Size(500);

    @Test
    public void create_whereValid_ShouldContainSize() {
        Rfq rfq = new Rfq(SIZE, EUR);
        assertThat(rfq.getSize()).isEqualTo(SIZE);
    }

    @Test
    public void create_whereValid_ShouldContainPaymentCurrency() {
        Rfq rfq = new Rfq(SIZE, USD);
        assertThat(rfq.getPaymentCurrency()).isEqualTo(USD);
    }

    @Test(expected = NullPointerException.class)
    public void create_wherePaymentCurrencyIsNull_ShouldRaiseError() {
        new Rfq(SIZE, null);
    }

    @Test(expected = NullPointerException.class)
    public void create_whereSizeIsNull_ShouldRaiseError() {
        new Rfq(null, GBP);
    }


}