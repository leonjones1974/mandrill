package uk.camsw.mandrill;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SizeTest {

    @Test
    public void create_WhereSizeIsValid_ShouldExposeSizeAsIntValue() {
        assertThat(new Size(400).intValue()).isEqualTo(400);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_WhereSizeIsNotMultipleOf100_ShouldRaiseError() {
        new Size(101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_WhereAmountIsNegative_ShouldRaiseError() {
        new Size(-100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_WhereAmountIsZero_ShouldRaiseError() {
        new Size(0);
    }


    @Test
    public void equals_IsOnSize() {
        assertThat(new Size(100))
                .isEqualTo(new Size(100))
                .isNotEqualTo(new Size(200));

    }

}