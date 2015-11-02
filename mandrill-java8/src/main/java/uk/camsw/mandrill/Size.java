package uk.camsw.mandrill;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * A number of possible ways to ensure the size validation logic is DRY.
 * I opted for encapsulating it in the domain model, being probably the simplest approach
 */
public class Size {
    private static int ORDER_SIZE_MULTIPLE = 100;
    private final int size;

    public Size(int size) {
        this.size = checkCorrectMultiple(size);
    }

    public int intValue() {
        return size;
    }

    private int checkCorrectMultiple(int amount) {
        if (amount > 0 && amount % ORDER_SIZE_MULTIPLE == 0) {
            return amount;
        }

        String message = String.format("Amount must be a positive and a multiple of: [%d]", ORDER_SIZE_MULTIPLE);
        throw new IllegalArgumentException(message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size that = (Size) o;

        return Objects.equal(this.size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(size);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("size", size)
                .toString();
    }
}
