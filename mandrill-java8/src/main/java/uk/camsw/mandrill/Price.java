package uk.camsw.mandrill;

import com.google.common.base.Objects;

import java.math.BigDecimal;

public class Price implements Comparable<Price> {

    private static int SCALE = 2;
    private final BigDecimal amount;

    public Price(String amount) {
        this(new BigDecimal(amount));
    }

    private Price(BigDecimal amount) {
        this.amount = amount.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public boolean isLessThanOrEqualTo(Price other) {
        return equals(other) || compareTo(other) < 0;
    }

    public boolean isGreaterThanOrEqualTo(Price other) {
        return equals(other) || compareTo(other) > 0;
    }

    public Price add(Price other) {
        return other == null
                ? new Price(amount.toString())
                : new Price(amount.add(other.amount));
    }

    @Override
    public int compareTo(Price other) {
        return amount.compareTo(other.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price other = (Price) o;

        return amount.compareTo(other.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    @Override
    public String toString() {
        return String.format("%." + SCALE + "f", amount.floatValue());
    }

}