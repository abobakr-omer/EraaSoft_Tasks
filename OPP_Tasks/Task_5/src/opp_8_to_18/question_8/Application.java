package opp_8_to_18.question_8;

public class Application {

    public static void main(String[] args) {

        FacebookPost facebookPost=new FacebookPost(5,"AboBakr","Hello i'm a Java developer");


        LinkedInPost linkedInPost =new LinkedInPost(10,"Ahmed","Hello i'm a Teacher");


        facebookPost.printPost();
        System.out.println("=====================================");
        linkedInPost.printPost();


    }


}
