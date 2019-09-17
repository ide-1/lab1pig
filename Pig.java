import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

/*
 * The Pig game
 * See http://en.wikipedia.org/wiki/Pig_%28dice_game%29
 *
 */
public class Pig {

    public static void main(String[] args) {
        new Pig().program();
    }

    // The only allowed instance variables (i.e. declared outside any method)
    // Accessible from any method
    final Scanner sc = new Scanner(in);
    final Random rand = new Random();

    void program() {
        //test();                 // <-------------- Uncomment to run tests!

        final int winPts = 20;    // Points to win (decrease if testing)
        Player[] players;         // The players (array of Player objects)
        Player current;            // Current player for round (must use)
        boolean aborted = false;// Game aborted by player?

        welcomeMsg(winPts);

        /*players = new Player[2];   // Hard coded players, replace *last* of all with ... (see below)
        Player p1 = new Player();
        p1.name = sc.nextLine();
        Player p2 = new Player();
        p2.name = sc.nextLine();
        players[0] = p1;
        players[1] = p2;*/
        int dice = 0;

        players = getPlayers();  // ... this (method to read in all players)

        statusMsg(players);
        Random rand = new Random();

        int i = rand.nextInt(players.length);
        current = players[i];   // TODO Set random player to start
        // TODO Game logic, using small step, functional decomposition
        //int dice = rollDice();
        int n = i;
        while ((current.totalPts + current.roundPts) < winPts) {
            String a = getPlayerChoice(current);

            //int dice = action(current, players, a);

            if (a.equals("r")) {
                dice = rollDice();
            } else if (a.equals("n")) {

                current = next(current, players);
            } else if (a.equals("q")) {
                aborted = quit(current);
                break;
            } else {
                out.println("wrong input");
            }

//braek om nåån vinner och input av spelare random person ska börja q ska fungera
            if (dice > 1) {
                current.roundPts += dice;
                roundMsg(dice, current);
            } else if (dice == 1) {
                current.roundPts = 0;
                roundMsg(dice, current);

                current = next(current, players);
            } /* else  if (dice == 0){
                current = next(current, players);
            } */
            //roundMsg(dice, current);
            dice = 0;
        }

        //out.println(p1.roundPts);
        gameOverMsg(current, aborted);

    }

    // ---- Game logic methods --------------

    // TODO
    int rollDice() {
        Random rand = new Random();
        int a = rand.nextInt(6);
        //out.println("you rolled " + (a + 1));
        return a + 1;
    }

    Pig.Player next(Player current, Player[] players) {
        current.totalPts = current.totalPts + current.roundPts;
        current.roundPts = 0;
        for (int f = 0; f < players.length; f++) {
            out.print("Points: " + players[f].name + " = " + players[f].totalPts + " ");
        }
        out.println();
        for (int x = 0; x < players.length; x++) {
            if (current == players[x]) {
                x = x+1;
                if (x >= players.length) {
                    x = 0;
                }
                current = players[x];
            }
        }
        return current;
    }
    //return current;


    /*int action(Player current, Player[] players, String a) {
        if (a.equals("r")) {
            int b = rollDice();
            return b;
        } else if (a.equals("n")) {
            next(current, players, n);
        } else if (a.equals("q")) {
            quit(current);
        }
        return 0;
    }*/

    boolean quit(Player current) {
        //gameOverMsg(current, true);
        return true;
    }
    // ---- IO methods ------------------

    void welcomeMsg(int winPoints) {
        out.println("Welcome to PIG!");
        out.println("First player to get " + winPoints + " points will win!");
        out.println("Commands are: r = roll , n = next, q = quit");
        out.println();
    }

    void statusMsg(Player[] players) {
        out.print("Points: ");
        for (int i = 0; i < players.length; i++) {
            out.print(players[i].name + " = " + players[i].totalPts + " ");
        }
        out.println();
    }

    void roundMsg(int result, Player current) {
        if (result > 1) {
            out.println("Got " + result + " running total are " + current.roundPts);
        } else if (result == 1) {
            out.println("Got 1 lost it all!");
        }
    }

    void gameOverMsg(Player player, boolean aborted) {
        if (aborted) {
            out.println("Aborted");

        } else {
            out.println("Game over! Winner is player " + player.name + " with "
                    + (player.totalPts + player.roundPts) + " points");
        }
    }

    String getPlayerChoice(Player player) {
        out.print("Player is " + player.name + " > ");
        return sc.nextLine();
    }

    Player[] getPlayers() {
        out.print("How many players? >");
        int i = sc.nextInt();
        Player[] players = new Player[i];
        sc.nextLine();
        for (int x = 0; x <= i - 1; x++) {
            players[x] = new Player();

            out.print("Name of player " + (x + 1) + "? >");
            players[x].name = sc.nextLine();

        }
        return players;

    }


    // ---------- Class -------------
// Class representing the concept of a player
// Use the class to create (instantiate) Player objects
    class Player {
        String name;     // Default null
        int totalPts;    // Total points for all rounds, default 0
        int roundPts;    // Points for a single round, default 0
    }

    // ----- Testing -----------------
    // Here you run your tests i.e. call your game logic methods
    // to see that they really work (IO methods not tested here)
    void test() {
        // This is hard coded test data
        // An array of (no name) Players (probably don't need any name to test)
        //Player[] players = {new Player(), new Player(), new Player()};

        // TODO Use for testing of logcial methods (i.e. non-IO methods)
        /*for (int i = 0; i < 20; i++)
            rollDice();

        exit(0);  */ // End program
        int dice = 1;
        Player current;
        Player[] players;         // The players (array of Player objects)

        players = new Player[2];   // Hard coded players, replace *last* of all with ... (see below)
        Player p1 = new Player();
        p1.name = "Olle";
        Player p2 = new Player();
        p2.name = "Fia";
        players[0] = p1;
        players[1] = p2;
        current = p1;
        if (dice > 1) {
            current.roundPts += dice;
        } else {
            // current = next(current, players);
        }
        out.println(current.name);
    }
}



