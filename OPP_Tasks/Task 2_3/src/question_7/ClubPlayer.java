package question_7;

public class ClubPlayer extends Player{

    private Integer code;

    public ClubPlayer(Integer id, String name, Integer number,Integer code) {
        super(id, name, number);
        setCode(code);
    }

    public void setCode(Integer code) {
        if (code!=null && code>0){
            this.code=code;
        }
        else {
            System.out.println("Code must be greater than Zero");
        }
    }

    public Integer getCode() {
        return code;
    }

    public void printPlayerData() {
        System.out.println("ID     : " + getId());
        System.out.println("Name   : " + getName());
        System.out.println("Number : " + getNumber());
        System.out.println("Fcode  : " + getCode());
    }


}
