package simple.dtu.pay;



import java.util.*;

public class SimpleDTUPayService {


    private SimpleDTUPay simpleDTUPay = new SimpleDTUPay();
    HashMap<Integer, SimpleDTUPay> transactions = new HashMap<>();

    public SimpleDTUPayService() {
        System.out.println("SimpleDTUPayService Created");
    }

    public boolean transaction(int amount, String cid, String mid) {
        if(cid.startsWith("m") || mid.startsWith("c")) {
            throw new IllegalArgumentException("customer with id "+mid+" is unknown");
        }
        simpleDTUPay.setAmount(amount);
        simpleDTUPay.setCid(cid);
        simpleDTUPay.setMid(mid);
        transactions.put(generateId(), simpleDTUPay);
        return true;
    }

    public HashMap<Integer, SimpleDTUPay> getTransactions() {
        return transactions;
    }
    public int generateId() {
        return transactions.size() + 1;
    }




}

