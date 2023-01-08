import io.cucumber.java.en.*;
import simple.dtu.pay.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MyStepdefs {
    String cid, mid;
    SimpleDTUPay dtupay = new SimpleDTUPay();
    SimpleDTUPayService dtupayService = new SimpleDTUPayService();
    HashMap<Integer, SimpleDTUPay> transactions;
    boolean successful;
    private ErrorMessageHolder errorMessageHolder = new ErrorMessageHolder();


    @Given("a customer with id {string}")
    public void a_customer_with_id(String cid) {
        this.cid = cid;

    }
    @Given("a merchant with id {string}")
    public void a_merchant_with_id(String mid) {
        this.mid = mid;
    }

    @When("the merchant initiates a payment for {int} kr by the customer")
    public void the_merchant_initiates_a_payment_for_kr_by_the_customer(Integer amount) {
        successful = dtupayService.transaction(amount, cid, mid);
    }

    @Then("the payment is successful")
    public void the_payment_is_successful() {
        assertTrue(successful);
    }

    @Given("a successful payment of {string} kr from customer {string} to merchant {string}")
    public void a_successful_payment_of_kr_from_customer_to_merchant(String string, String string2, String string3) {
        assertTrue(dtupayService.transaction(Integer.parseInt(string), string2, string3));
    }
    @When("the manager asks for a list of payments")
    public void the_manager_asks_for_a_list_of_payments() {
        transactions = dtupayService.getTransactions();
    }
    @Then("the list contains a payments where customer {string} paid {string} kr to merchant {string}")
    public void the_list_contains_a_payments_where_customer_paid_kr_to_merchant(String string, String string2, String string3) {
        // iterate through the list of transactions and check if the transaction is there
        boolean found = false;
        for (SimpleDTUPay transaction : transactions.values()) {
            if (transaction.getCid().equals(string) && transaction.getMid().equals(string3) && transaction.getAmount() == Integer.parseInt(string2)) {
                found = true;
            }
        }
        assertTrue(found);
    }

    @When("the merchant initiates a payment for {string} kr by the customer")
    public void the_merchant_initiates_a_payment_for_kr_by_the_customer(String string) {
        try {
            successful = dtupayService.transaction(Integer.parseInt(string), mid, cid);
        } catch (Exception e) {
            errorMessageHolder.setErrorMessage(e.getMessage());
        }
    }

    @Then("the payment is not successful")
    public void the_payment_is_not_successful() {
        assertFalse(successful);
    }
    @Then("an error message is returned saying {string}")
    public void an_error_message_is_returned_saying(String string) {
        assertEquals(string, errorMessageHolder.getErrorMessage());
    }
}
