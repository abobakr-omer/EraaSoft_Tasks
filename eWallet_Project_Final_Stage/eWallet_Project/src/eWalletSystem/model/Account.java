package eWalletSystem.model;

public class Account {
    private String userName;
    private String password;
    private Double balance;
    private String phoneNumber; // +201113903660
    private Integer age;
    private boolean isAdmin;

    public Account() {
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    public Account(String userName, String password, String phoneNumber, Integer age) {
        this.userName = userName;
        this.password = password;
        this.balance = .0;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    // Admin constructor
    public Account(String userName, String password, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.balance = 0.0;
        this.phoneNumber = null;
        this.age = 0;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
