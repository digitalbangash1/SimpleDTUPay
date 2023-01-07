package simple.dtu.pay;

public class UnknownCustomerException extends Exception {
    public UnknownCustomerException(String s) {
        super("customer with id " + s + " is unknown");
    }
}
