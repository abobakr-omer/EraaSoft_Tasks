package opp_8_to_18.question_8;

public class LinkedInPost implements Post{
    private int id;
    private String name;
    private String text;

    public LinkedInPost(int id,String name,String text){
        this.id = id;
        this.name = name;
        this.text = text;
    }



    @Override
    public void printPost() {
        System.out.println("You are on application LinkedIn");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Text: " + text);
    }
}
