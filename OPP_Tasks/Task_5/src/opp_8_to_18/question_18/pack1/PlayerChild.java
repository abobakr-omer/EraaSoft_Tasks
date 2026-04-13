package opp_8_to_18.question_18.pack1;

public class PlayerChild extends PlayerBenfit{


    public PlayerChild(int id, double benfitPlayer, double allPlayerbenfit) {
        super(id, benfitPlayer, allPlayerbenfit);
    }

    public void printData() {
        System.out.println("id = " + id);
        System.out.println("benfitPlayer = " + benfitPlayer);
        System.out.println("allPlayerbenfit = " + allPlayerbenfit);
    }


}
