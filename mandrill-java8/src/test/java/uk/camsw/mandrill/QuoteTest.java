package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteTest {

    private static Price BID = new Price("123.50");
    private static Price ASK = new Price("124.20");

    @Test
    public void create_WhereValid_ShouldContainCsBuyPrice() {
        assertThat(new Quote(BID, ASK).getCsBidPrice()).isEqualTo(BID);
    }

    @Test
    public void create_WhereValid_ShouldContainCsSellPrice() {
        assertThat(new Quote(BID, ASK).getCsAskPrice()).isEqualTo(ASK);
    }

    @Test(expected = NullPointerException.class)
    public void create_WhereCsBuyIsNull_ShouldRaiseError() {
        new Quote(null, ASK);
    }

    @Test(expected = NullPointerException.class)
    public void create_WhereCsSellIsNull_ShouldRaiseError() {
        new Quote(BID, null);
    }


    @Test
    public void equals_ShouldBeOnBothBuyAndSell() {
        assertThat(new Quote(BID, ASK))
                .isEqualTo(new Quote(BID, ASK))
                .isNotEqualTo(new Quote(new Price("100"), ASK))
                .isNotEqualTo(new Quote(BID, new Price("100")));
    }
}