package opp_8_to_18.question_18.pack2;

public class CaptainChild extends CaptainBenfit{

    public CaptainChild(int id, double benfitCaptain, double allCaptainbenfit) {
        super(id, benfitCaptain, allCaptainbenfit);
    }

    public void printData() {
        System.out.println("id = " + id);
        System.out.println("benfitCaptain = " + benfitCaptain);
        System.out.println("allCaptainbenfit = " + allCaptainbenfit);
    }


}
