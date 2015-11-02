package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SizeTest {

    @Test
    public void create_whereSizeIsValid_ShouldExposeSizeAsIntValue() {
        assertThat(new Size(400).intValue()).isEqualTo(400);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_whereSizeIsNotMultipleOf100_ShouldRaiseError() {
        new Size(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_whereAmountIsNegative_ShouldRaiseError() {
        new Size(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_whereAmountIsZero_ShouldRaiseError() {
        new Size(0);
    }

}