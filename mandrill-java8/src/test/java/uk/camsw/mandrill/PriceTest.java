package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceTest {

    @Test(expected = IllegalArgumentException.class)
    public void itShould_RaiseError_IfAmountIsNotDecimal() {
        new Price("not_decimal");
    }

    @Test(expected = NullPointerException.class)
    public void itShould_RaiseError_IfAmountIsNul() {
        new Price(null);
    }

    @Test
    public void equals_ShouldBeAccurateTo2dp_HalfRoundUp() {
        assertThat(new Price("120.011"))
                .isEqualTo(new Price("120.014"))
                .isNotEqualTo(new Price("120"))
                .isNotEqualTo(new Price("120.015"));
    }

    @Test
    public void isLessThanOrEqualTo_ShouldBeAccurateTo2dp_HalfRoundUp() {
        assertThat(new Price("120.01").isLessThanOrEqualTo(new Price("120.00"))).isFalse();
        assertThat(new Price("120.01").isLessThanOrEqualTo(new Price("120.014"))).isTrue();
        assertThat(new Price("120.01").isLessThanOrEqualTo(new Price("120.015"))).isTrue();
    }

    @Test
    public void isGreaterThanOrEqualTo_ShouldBeAccurateTo2dp_HalfRoundUp() {
        assertThat(new Price("120.01").isGreaterThanOrEqualTo(new Price("120.015"))).isFalse();
        assertThat(new Price("120.01").isGreaterThanOrEqualTo(new Price("120.014"))).isTrue();
        assertThat(new Price("120.01").isGreaterThanOrEqualTo(new Price("120"))).isTrue();
    }

    @Test
    public void add_Should_AddPrices() {
        assertThat(new Price("12.45").add(new Price("13.54")))
                .isEqualTo(new Price("25.99"));
    }

    @Test
    public void add_Should_ReturnSelfWhereOtherIsNull() {
        assertThat(new Price("12.45").add(null))
                .isEqualTo(new Price("12.45"));
    }

}