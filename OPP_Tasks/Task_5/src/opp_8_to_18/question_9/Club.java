package opp_8_to_18.question_9;

import java.util.ArrayList;

public class Club {

    private String clubName;
    private ArrayList<Player> players=new ArrayList<>();

    public Club(String clubName){
        this.clubName=clubName;
        this.players=new ArrayList<>();
    }

    public void savePlayer(Player player){
        players.add(player);
    }

    public void showPlayers(){
        System.out.println("Club Name: " + clubName);
        for (Player player: players ){
            player.printPlayerData();
            System.out.println("==========================");
        }
    }



}
