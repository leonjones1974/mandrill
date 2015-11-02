package uk.camsw.mandrill;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * In reality you'd likely carry a lot more information in the quote, but in the absence of an execution
 * requirement I've exposed the minimum required to test the matching logic requirement
 */
public class Quote {

    public final Price csBuy;
    public final Price csSell;

    public Quote(Price csBuy, Price csSell) {
        this.csBuy = checkNotNull(csBuy);
        this.csSell = checkNotNull(csSell);
    }

    public Price getCsBuyPrice() {
        return csBuy;
    }

    public Price getCsSellPrice() {
        return csSell;
    }
}
