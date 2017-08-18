package driver;

import com.Dice;
import com.Game;
import com.Player;

import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Player[] players = {
                new Player("Jack"),
                new Player("Jill")
        };
        Game game = new Game(players, 5, 5);

        int turn = 0;
        System.out.println(game);
        int winnerId = -1;
        while (winnerId == -1) {
            System.out.print("Hey " + players[turn] + "! Hit enter when you are ready to roll a dice: ");
            in.nextLine();
            winnerId = game.play(turn, Dice.roll());
            turn = (turn + 1) % players.length;
        }
        System.out.println("Yo! " + players[winnerId] + " won this round!");
    }
}
