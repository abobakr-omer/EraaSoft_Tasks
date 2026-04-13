package question_7;

public class Player {

    private Integer id;
    private Integer number;
    private String name;

    public Player(Integer id, String name, Integer number) {
        setId(id);
        setName(name);
        setNumber(number);
    }

    public void setId(Integer id) {
        if (id!=null && id > 0) this.id = id;
        else System.out.println("ID must be greater than 0");
    }

    public void setName(String name) {
        if (name != null && name.matches("[a-zA-Z ]+") && name.length() >= 3) {
            this.name = name;
        } else {
            System.out.println("Name must be at least 3 characters and contain only letters and spaces");
        }
    }

    public void setNumber(Integer number) {
        if (number != null && number >= 0 && number <= 99) {
            this.number = number;
        } else {
            System.out.println("Player number must be between [0 and 99] inclusive");
        }
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void printBasicData(String title) {
        System.out.println("ID     : " + getId());
        System.out.println("Name   : " + getName());
        System.out.println("Number : " + getNumber());
    }

}
