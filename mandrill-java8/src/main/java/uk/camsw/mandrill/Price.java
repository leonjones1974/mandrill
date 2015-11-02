package uk.camsw.mandrill;

import com.google.common.base.Objects;

import java.math.BigDecimal;

public class Price implements Comparable<Price> {

    private static int SCALE = 2;
    private final BigDecimal amount;

    public static Price max(Price p1, Price p2) {
        if (p1 == null && p2 == null) return null;
        if (p1 == null) return new Price(p2.amount);
        if (p2 == null) return new Price(p1.amount);
        return new Price(p1.amount.max(p2.amount));
    }

    public static Price min(Price p1, Price p2) {
        if (p1 == null && p2 == null) return null;
        if (p1 == null) return new Price(p2.amount);
        if (p2 == null) return new Price(p1.amount);
        return new Price(p1.amount.min(p2.amount));
    }

    public Price(String amount) {
        this(new BigDecimal(amount));
    }

    private Price(BigDecimal amount) {
        this.amount = amount.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public boolean isLessThan(Price other) {
        return compareTo(other) < 0;
    }

    public boolean isGreaterThan(Price other) {
        return compareTo(other) > 0;
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