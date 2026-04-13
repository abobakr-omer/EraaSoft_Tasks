package opp_8_to_18.question_16;

public class Gift {

    int giftId;
    String giftName;

    public Gift(int giftId, String giftName) {
        this.giftId = giftId;
        this.giftName = giftName;
    }

    public void printGiftData() {
        System.out.println("Gift Id: " + giftId);
        System.out.println("Gift Name: " + giftName);
    }


}
