package uk.camsw.mandrill;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * In reality you'd likely carry a lot more information in the quote, but in the absence of an execution
 * requirement I've exposed the minimum required to test the matching logic requirement
 */
public class Quote {

    public final Price csBidPrice;
    public final Price csAskPrice;

    public Quote(Price csBidPrice, Price csAskPrice) {
        this.csBidPrice = checkNotNull(csBidPrice);
        this.csAskPrice = checkNotNull(csAskPrice);
    }

    public Price getCsBidPrice() {
        return csBidPrice;
    }

    public Price getCsAskPrice() {
        return csAskPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote that = (Quote) o;

        return Objects.equal(this.csBidPrice, that.csBidPrice) &&
                Objects.equal(this.csAskPrice, that.csAskPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(csBidPrice, csAskPrice);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("csBidPrice", csBidPrice)
                .add("csAskPrice", csAskPrice)
                .toString();
    }
}
