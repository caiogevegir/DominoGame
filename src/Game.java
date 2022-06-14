import java.util.Scanner;

public class Game {

    public static int numOpeningDominoes = 7;

    public static void main(String[] args) {
        // Init
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter your name:");
        String userName = userInput.next();
        System.out.println("Welcome, " + userName + "! :)");

        // Setup
        UserPlayer userPlayer = new UserPlayer(userName);
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
        Player[] playerOrder = makeFirstMovementAndSetPlayersOrder(userPlayer, comPlayer, table, deck);
        // Game finishes when a player has no dominoes on hand, or deck is empty and players cannot place pieces on the table
        while ( userPlayer.getNumDominoesOnHand() != 0 && comPlayer.getNumDominoesOnHand() != 0 && (!deck.isEmpty() || userPlayer.hasPlayableDominoOnHand(table) || comPlayer.hasPlayableDominoOnHand(table)) ) {
            for ( Player p : playerOrder ) {
                System.out.println(p.getName() + "'s turn...");
                table.print();
                if ( !p.hasPlayableDominoOnHand(table) ) {
                    System.out.println(p.getName() + " doesn't have a domino to place and have to draw...");
                    p.drawCardsUntilPlay(deck, table);
                    p.printHand();
                } else {
                    System.out.println(p.getName() + "'s turn, choose a piece!");
                    p.printHand();
                    p.makeMovement(deck, table);
                    if ( p.getNumDominoesOnHand() == 0 ) {
                        break;
                    }
                }
            }
        }

        // Finishing Game
        if ( userPlayer.getNumDominoesOnHand() == 0 ) {
            System.out.println(userPlayer.getName() + " WINS by placing all its pieces!");
        } else if ( comPlayer.getNumDominoesOnHand() == 0 ) {
            System.out.println(comPlayer.getName() + " WINS by placing all its pieces!");
        } else if ( deck.isEmpty() ) {
            if ( userPlayer.getNumDominoesOnHand() > comPlayer.getNumDominoesOnHand() ) {
                System.out.println(userPlayer.getName() + " WINS by having less pieces on hand!");
            } else if ( comPlayer.getNumDominoesOnHand() > userPlayer.getNumDominoesOnHand() ) {
                System.out.println(comPlayer.getName() + " WINS by having less pieces on hand!");
            } else {
                System.out.println("Both players have the same number of pieces on hand. The game is a DRAW!");
            }
        }
    }

    public static Player[] makeFirstMovementAndSetPlayersOrder(UserPlayer userPlayer, ComPlayer comPlayer, Table table, Deck deck) {
        int d;
        Player[] playerOrder = new Player[2];
        for ( d=6; d>=0; d-- ) {
            Domino domino = new Domino(d,d);
            int dominoIndex = userPlayer.searchDominoOnHand(domino);
            if ( dominoIndex != -1 ) {
                System.out.println(userPlayer.getName() + " starts the game with a " + domino.renderToString());
                userPlayer.placeDominoOnTable(dominoIndex, table);
                playerOrder[0] = comPlayer;
                playerOrder[1] = userPlayer;
                break;
            } else {
                dominoIndex = comPlayer.searchDominoOnHand(domino);
                if ( dominoIndex != -1 ) {
                    System.out.println(comPlayer.getName() + " starts the game with a " + domino.renderToString());
                    comPlayer.placeDominoOnTable(dominoIndex, table);
                    playerOrder[0] = userPlayer;
                    playerOrder[1] = comPlayer;
                    break;
                }
            }
        }

        if ( d < 0 ) { // No doubles on either players hand
            System.out.println("No player had doubles. Game starts with top domino from deck:");
            table.addDominoToTable(deck.drawDomino(), "");
            playerOrder[0] = userPlayer;
            playerOrder[1] = comPlayer;
        }

        return playerOrder;
    }
}
