package opp_8_to_18.question_13;

public class Player {

    private int id;
    private String name;
    private Shirt shirt;

    public Player(int id,String name,Shirt shirt){
        this.id=id;
        this.name=name;
        this.shirt=shirt;
    }

    public void printPlayerData(){
        System.out.println("Player Id: " + id);
        System.out.println("Player Name: " + name);
        shirt.printShirt();

        System.out.println("================================");
    }


}
