package simple.dtu.pay;

import dtu.ws.fastmoney.BankService;
import dtu.ws.fastmoney.BankServiceService;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private BankService bankService = new BankServiceService().getBankServicePort();
    private String knownCustomerId = "cid1";
    private String knownMerchantId = "mid1";
    private List<simple.dtu.pay.Payment> payments = new ArrayList<>();

    public simple.dtu.pay.Payment savePayment(Payment payment) throws UnknownCustomerException, UnknownMerchantException {
        if (!payment.getCustomerId().equals(knownCustomerId)) {
            throw new UnknownCustomerException("Customer with id " + payment.getCustomerId() + " is unknown");
        }
        if (!payment.getMerchantId().equals(knownMerchantId)) {
            throw new UnknownMerchantException("Merchant with id " + payment.getMerchantId() + " is unknown");
        }

        payments.add(payment);
        return payment;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void deletePayments() {
        payments = new ArrayList<>();
    }

}
