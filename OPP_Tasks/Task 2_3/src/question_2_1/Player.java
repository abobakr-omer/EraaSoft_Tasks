package question_2_1;

public class Player {

    String playerName;
    int playerNumber;

    Player(String playerName, int playerNumber) {
        if (playerName == null || playerName.length() <= 5)
            throw new IllegalArgumentException("question_2.Player name must exceeds 5 characters");
        if (playerNumber <= 0)
            throw new IllegalArgumentException("question_2.Player number must be greater than zero");
        this.playerName = playerName;
        this.playerNumber = playerNumber;

    }


    public void printPlayerData() {
        System.out.println("question_2.Player Name: " + this.playerName);
        System.out.println("question_2.Player Number: " + this.playerNumber);

    }


}
