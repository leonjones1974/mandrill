package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteTest {

    private static Price CS_BUY = new Price("123.50");
    private static Price CS_SELL = new Price("124.20");

    @Test
    public void create_whereValid_ShouldContainCsBuyPrice() {
        assertThat(new Quote(CS_BUY, CS_SELL).getCsBuyPrice()).isEqualTo(CS_BUY);
    }

    @Test
    public void create_whereValid_ShouldContainCsSellPrice() {
        assertThat(new Quote(CS_BUY, CS_SELL).getCsSellPrice()).isEqualTo(CS_SELL);

    }

    @Test(expected = NullPointerException.class)
    public void create_whereCsBuyIsNull_ShouldRaiseError() {
        new Quote(null, CS_SELL);
    }

    @Test(expected = NullPointerException.class)
    public void create_whereCsSellIsNull_ShouldRaiseError() {
        new Quote(CS_BUY, null);
    }

}