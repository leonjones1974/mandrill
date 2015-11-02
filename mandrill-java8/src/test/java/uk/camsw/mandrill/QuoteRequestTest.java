package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.camsw.mandrill.Ccy.*;

public class QuoteRequestTest {

    private static final Size SIZE = new Size(500);

    @Test
    public void create_whereValid_ShouldContainSize() {
        QuoteRequest quoteRequest = new QuoteRequest(SIZE, EUR);
        assertThat(quoteRequest.getSize()).isEqualTo(SIZE);
    }

    @Test
    public void create_whereValid_ShouldContainPaymentCurrency() {
        QuoteRequest quoteRequest = new QuoteRequest(SIZE, USD);
        assertThat(quoteRequest.getPaymentCurrency()).isEqualTo(USD);
    }

    @Test(expected = NullPointerException.class)
    public void create_wherePaymentCurrencyIsNull_ShouldRaiseError() {
        new QuoteRequest(SIZE, null);
    }

    @Test(expected = NullPointerException.class)
    public void create_whereSizeIsNull_ShouldRaiseError() {
        new QuoteRequest(null, GBP);
    }


}