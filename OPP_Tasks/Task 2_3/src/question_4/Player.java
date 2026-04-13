package question_4;

public class Player extends Person {

    private Integer number;

    Player(Integer id, String name, Integer number) {
        super(id, name);
        setNumber(number);
    }


    public void setNumber(Integer number) {
        if (number != null && number >= 0 && number <= 99) {
            this.number = number;
        } else {
            System.out.println("Player number must be between [0 and 99] inclusive");
        }
    }

    public Integer getNumber() {
        return number;
    }


    public void printPlayerData() {
        System.out.println("Player:");
        System.out.println("  Name : " + getName());
        System.out.println("  ID   : " + getId());
        System.out.println("  Number: " + getNumber());
    }


}
