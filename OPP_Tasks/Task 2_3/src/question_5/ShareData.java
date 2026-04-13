package question_5;

public class ShareData extends BaseEntity {

    private String phoneNumber;

    ShareData(String name, Integer id, String phoneNumber) {
        super(name, id);
        setPhoneNumber(phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.matches("\\+20\\d{11}")) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Phone Number must start with +20 and its length is 13 characters");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}
