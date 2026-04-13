package opp_8_to_18.question_9;

public class PrivateClub extends Club{

    public PrivateClub(String clubName) {
        super(clubName);
    }

    @Override
    public void savePlayer(Player player) {
        System.out.println("Saving player in Public Club");
        super.savePlayer(player);
    }
}
