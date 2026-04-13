package opp_8_to_18.question_12;

public class Facebook {

    private int id;
    private String text;
    private String image;


    public Facebook(int id,String text){
        this.id=id;
        this.text=text;
    }

    public Facebook(int id,String text,String image){
        this.id=id;
        this.text=text;
        this.image=image;
    }

    public void showPost(){
        System.out.println("Id: " + id);
        System.out.println("Text: " + text);

        if (image!=null){
            System.out.println("Image: " + image);
        }

        System.out.println("=================================");

    }


}
