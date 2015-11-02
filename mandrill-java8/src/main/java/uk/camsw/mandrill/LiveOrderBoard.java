package uk.camsw.mandrill;

import java.util.Collection;

interface LiveOrderBoard {

    Collection<Order> findOrders(Ccy paymentCurrency);

}
