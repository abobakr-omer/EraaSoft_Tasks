package opp_8_to_18.question_9;

public class PublicClub extends Club{
    public PublicClub(String clubName) {
        super(clubName);
    }


    @Override
    public void savePlayer(Player player) {
        System.out.println("Saving player in Private Club");
        super.savePlayer(player);
    }
}
