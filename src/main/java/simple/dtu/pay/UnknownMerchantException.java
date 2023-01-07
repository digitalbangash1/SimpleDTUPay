package simple.dtu.pay;

public class UnknownMerchantException extends Exception {
    public UnknownMerchantException(String s) {
        super("merchant with id " + s + " is unknown");
    }
}
