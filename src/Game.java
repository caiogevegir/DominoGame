import java.util.Scanner;

public class Game {

    public static int numOpeningDominoes = 7;

    public static void main(String[] args) {
        // Setup
        System.out.println("Enter your name:");
        UserPlayer userPlayer = getUserPlayer();
        System.out.println("Inviting computer to a new game...");
        ComPlayer comPlayer = new ComPlayer();
        System.out.println("Cleaning up the table...");
        Table table = new Table();
        System.out.println("Taking dominoes off the case...");
        Deck deck = new Deck();
        System.out.println("Drawing opening hand...");
        comPlayer.drawOpeningHand(deck);
        userPlayer.drawOpeningHand(deck);

        // Starting Game
        Player[] playerOrder = makeFirstMovementAndDefinePlayersOrder(userPlayer, comPlayer, table, deck);
        Player winnerByEmptyHand = null;
        Player winnerByLessPiecesOnHand = null;
        // Game finishes when a player has no dominoes on hand, or deck is empty and players cannot place pieces on the table
        gameLoop:
        while ( !(deck.isEmpty() && userPlayer.passedLastTurn && comPlayer.passedLastTurn) ) {
            for ( Player p : playerOrder ) {
                table.printDominoes();
                System.out.println(p.getName() + "'s turn...");
                p.printHand(deck);
                p.makeMovement(deck, table);
                if ( p.getNumDominoesOnHand() == 0 ) {
                    winnerByEmptyHand = p;
                    break gameLoop;
                }
                if ( deck.isEmpty() && userPlayer.passedLastTurn && comPlayer.passedLastTurn ) {
                    if ( userPlayer.getNumDominoesOnHand() > comPlayer.getNumDominoesOnHand() ) {
                        winnerByLessPiecesOnHand = userPlayer;
                    } else if ( comPlayer.getNumDominoesOnHand() > userPlayer.getNumDominoesOnHand() ) {
                        winnerByLessPiecesOnHand = comPlayer;
                    }
                }
            }
        }

        // Finishing Game
        table.printDominoes();
        if ( winnerByEmptyHand != null ) {
            System.out.println(winnerByEmptyHand.getName() + " WINS by placing all pieces!");
        } else if ( winnerByLessPiecesOnHand != null ) {
            System.out.println(winnerByLessPiecesOnHand.getName() + " WINS by having less pieces on hand!");
        } else {
            System.out.println("The match is a DRAW!");
        }
    }

    public static UserPlayer getUserPlayer() {
        Scanner userInput = new Scanner(System.in);
        String userName = userInput.next();
        System.out.println("Welcome, " + userName + "! :)");
        return new UserPlayer(userName);
    }

    public static Player[] makeFirstMovementAndDefinePlayersOrder(UserPlayer userPlayer, ComPlayer comPlayer, Table table, Deck deck) {
        int d;
        Player[] playerOrder = new Player[2];
        playerOrder[0] = userPlayer;
        playerOrder[1] = comPlayer;

        searchLoop:
        for ( d=0; d<=6; d++ ) {
            for ( int p=0; p<2; p++ ) {
                Domino domino = deck.getDouble(d);
                int id = playerOrder[p].searchDominoOnHand(domino);
                if ( id != -1 ) {
                    System.out.println(playerOrder[p].getName() + " starts the game with a " + domino.renderToString());
                    playerOrder[p].placeDominoOnTable(domino, table);
                    playerOrder[p].removeDominoFromHand(domino);
                    if ( p == 0 ) {
                        // Invert order
                        Player aux = playerOrder[0];
                        playerOrder[0] = playerOrder[1];
                        playerOrder[1] = aux;
                    }
                    break searchLoop;
                }
            }
        }

        if ( d > 6 ) { // No doubles on either players hand
            System.out.println("No player had doubles. Game starts with top domino from deck:");
            table.placeDominoOnLeft(deck.drawDomino());
        }

        return playerOrder;
    }
}
