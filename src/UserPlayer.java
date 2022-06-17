import java.util.Scanner;

public class UserPlayer extends Player {

    // Object Attributes
    private final Scanner userInput = new Scanner(System.in);

    // Constructors
    public UserPlayer(String name) {
        super.name = name;
    }

    // Public Methods
    @Override
    public void printHand(Deck deck) {
        int id = 0;
        for ( Domino d : this.hand ) {
            System.out.print((id++) + ":" + d.renderToString() + " ");
        }
        if ( !deck.isEmpty() ) {
            System.out.println(id + ": Draw");
        } else {
            System.out.println(id + ": Pass");
        }
    }

    public void makeMovement(Deck deck, Table table) {
        boolean dominoWasPlaced = false;
        Domino domino;
        while ( !dominoWasPlaced ) {
            int id = this.readDominoIdFromUser();
            if ( id == this.numDominoesOnHand ) {
                if ( deck.isEmpty() ) {
                    this.passedLastTurn = true;
                } else {
                    this.drawDominoAndPlay(deck, table);
                }
                break;
            } else {
                domino = this.hand.get(id);
                dominoWasPlaced = this.placeDominoOnTable(domino, table);
                if ( !dominoWasPlaced ) {
                    System.out.println("Sorry, this piece cannot be placed now...");
                }
                else {
                    System.out.println(this.name + " placed a " + domino.renderToString());
                    this.removeDominoFromHand(domino);
                }
            }
        }
    }

    // Private Methods
    private int readDominoIdFromUser() {
        int id = -1;
        System.out.println("Choose a domino:");
        while ( id < 0 || id > this.numDominoesOnHand ) {
            id = this.userInput.nextInt();
            if ( id < 0 || id > this.numDominoesOnHand ) {
                System.out.println("Please, choose a valid index [0-" + (this.numDominoesOnHand) + "]");
            }
        }
        return id;
    }

    @Override
    protected char readSideFromUser() {
        char side = '0';
        System.out.println("Choose whether place the piece on [L]eft or [R]ight side:");
        while ( side != 'L' && side != 'R' ) {
            side = this.userInput.next().toUpperCase().charAt(0);
            if ( side != 'L' && side != 'R' ) {
                System.out.println("Please, choose a valid side [L-R]");
            }
        }
        return side;
    }

}
