package opp_8_to_18.question_16;

public class Bill {

    int billId;
    double total;

    public Bill(int billId, double total) {
        this.billId = billId;
        this.total = total;
    }

    public void printBillData() {
        System.out.println("Bill Id: " + billId);
        System.out.println("Bill Total: " + total);
    }

}
