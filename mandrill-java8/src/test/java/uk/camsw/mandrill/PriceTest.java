package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceTest {

    @Test(expected = IllegalArgumentException.class)
    public void create_WhereAmountIsNotDecimal_ShouldRaiseError() {
        new Price("not_decimal");
    }

    @Test(expected = NullPointerException.class)
    public void create_WhereAmountIsNull_ShouldRaiseError() {
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
    public void isLessThan_ShouldBeAccurateTo2dp_HalfRoundUp() {
        assertThat(new Price("120.01").isLessThan(new Price("120.00"))).isFalse();
        assertThat(new Price("120.01").isLessThan(new Price("120.014"))).isFalse();
        assertThat(new Price("120.01").isLessThan(new Price("120.01"))).isFalse();
        assertThat(new Price("120.01").isLessThan(new Price("120.015"))).isTrue();
    }

    @Test
    public void isGreaterThan_ShouldBeAccurateTo2dp_HalfRoundUp() {
        assertThat(new Price("120.01").isGreaterThan(new Price("120.015"))).isFalse();
        assertThat(new Price("120.01").isGreaterThan(new Price("120.014"))).isFalse();
        assertThat(new Price("120.01").isGreaterThan(new Price("120.01"))).isFalse();
        assertThat(new Price("120.01").isGreaterThan(new Price("120"))).isTrue();
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

    @Test
    public void max_whereBothPricesAreNull_ShouldReturnNull() {
        assertThat(Price.max(null, null)).isNull();
    }

    @Test
    public void max_whereOnePriceIsNull_ShouldReturnTheOtherPrice() {
        assertThat(Price.max(new Price("123.00"), null)).isEqualTo(new Price("123.00"));
        assertThat(Price.max(null, new Price("123.00"))).isEqualTo(new Price("123.00"));
    }

    @Test
    public void max_whereBothPricesArePresent_ShouldReturnTheHigherPrice() {
        assertThat(Price.max(new Price("122.99"), new Price("123.00"))).isEqualTo(new Price("123.00"));
        assertThat(Price.max(new Price("123.01"), new Price("123.00"))).isEqualTo(new Price("123.01"));
    }

    @Test
    public void max_shouldReturnANewPriceInstance() {
        Price max = new Price("123.01");
        Price min = new Price("123.00");
        assertThat(Price.max(max, min)).isNotSameAs(max);
    }

    @Test
    public void min_whereBothPricesAreNull_ShouldReturnNull() {
        assertThat(Price.max(null, null)).isNull();
    }

    @Test
    public void min_whereOnePriceIsNull_ShouldReturnTheOtherPrice() {
        assertThat(Price.min(new Price("123.00"), null)).isEqualTo(new Price("123.00"));
        assertThat(Price.min(null, new Price("123.00"))).isEqualTo(new Price("123.00"));
    }

    @Test
    public void min_whereBothPricesArePresent_ShouldReturnTheLowerPrice() {
        assertThat(Price.min(new Price("122.99"), new Price("123.00"))).isEqualTo(new Price("122.99"));
        assertThat(Price.min(new Price("123.01"), new Price("123.00"))).isEqualTo(new Price("123.00"));
    }

    @Test
    public void min_shouldReturnANewPriceInstance() {
        Price max = new Price("123.01");
        Price min = new Price("123.00");
        assertThat(Price.min(max, min)).isNotSameAs(min);
    }

}