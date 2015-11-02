package uk.camsw.mandrill;

public class AmountValidation {
    private static int ORDER_SIZE_MULTIPLE = 100;

    public boolean isOrderSizeValid(int amount) {
        return amount > 0 && amount % ORDER_SIZE_MULTIPLE == 0;
    }
}
