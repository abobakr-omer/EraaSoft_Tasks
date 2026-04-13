package opp_8_to_18.question_9;

public class Application {

    public static void main(String[] args) {
        Player player1=new Player(1,"Ahmed");
        Player player2=new Player(2,"AboBakr");
        Player player3=new Player(3,"Mohamed");
        Player player4 =new Player(4,"Hema");

        PublicClub publicClub=new PublicClub("Barca public");
        PrivateClub privateClub=new PrivateClub("Real private");

        publicClub.savePlayer(player1);
        publicClub.savePlayer(player2);

        privateClub.savePlayer(player3);
        privateClub.savePlayer(player4);

        publicClub.showPlayers();

        privateClub.showPlayers();


    }


}
