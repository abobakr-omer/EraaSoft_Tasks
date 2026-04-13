package question_5;

public class Player extends ShareData {

    private Integer number;

    Player(String name, Integer id, String phoneNumber, Integer number) {
        super(name, id, phoneNumber);
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
        System.out.println("  ID    : " + getId());
        System.out.println("  Name  : " + getName());
        System.out.println("  Phone : " + getPhoneNumber());
        System.out.println("  Number: " + number);
    }


}
