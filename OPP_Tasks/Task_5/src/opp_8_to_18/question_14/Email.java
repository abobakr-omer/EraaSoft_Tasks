package opp_8_to_18.question_14;

public class Email {

    private String address;
    private String emailText;

    public Email(String address,String emailText){
        this.address=address;
        this.emailText=emailText;
    }

    public void printEmail(){
        System.out.println("Email Address: " + address);
        System.out.println("Email Text: " + emailText);
    }


}
