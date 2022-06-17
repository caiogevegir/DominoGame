import java.util.ArrayList;

public abstract class Player {

    // Object Attributes
    protected String name;
    protected ArrayList<Domino> hand = new ArrayList<>();
    protected int numDominoesOnHand = 0;
    public boolean passedLastTurn = false;

    // Getters
    public String getName() { return this.name; }
    public int getNumDominoesOnHand() { return this.numDominoesOnHand; }

    // Public Methods
    public void drawOpeningHand(Deck deck) {
        for ( int d=0; d<Game.numOpeningDominoes; d++ ) {
            this.drawDominoFromDeck(deck);
        }
    }

    public void drawDominoFromDeck(Deck deck) {
        if ( !deck.isEmpty() ) {
            this.hand.add(deck.drawDomino());
            this.numDominoesOnHand++;
        }
    }

    public void drawDominoAndPlay(Deck deck, Table table) {
        boolean dominoWasPlaced;
        this.drawDominoFromDeck(deck);
        Domino domino = this.hand.get(this.numDominoesOnHand-1);
        System.out.println("Drew a domino from deck");
        dominoWasPlaced = this.placeDominoOnTable(domino, table);
        if ( dominoWasPlaced ) {
            System.out.println(domino.renderToString() + " can be placed now!");
            this.removeDominoFromHand(domino);
        }
    }

    public int searchDominoOnHand(Domino domino) {
        return this.hand.indexOf(domino);
    }

    public abstract void makeMovement(Deck deck, Table table);
    public abstract void printHand(Deck deck);

    // Private Methods
    protected abstract char readSideFromUser();

    protected boolean placeDominoOnTable(Domino domino, Table table) {
        boolean dominoWasPlaced = true;
        if ( table.isEmpty() ) {
            table.placeDominoOnLeft(domino);
        } else if ( table.dominoCanBePlacedOnBothSides(domino) ) {
            char side = readSideFromUser();
            if ( side == 'L' ) {
                table.placeDominoOnLeft(domino);
            } else { // side == 'R'
                table.placeDominoOnRight(domino);
            }
        } else if ( table.dominoCanBePlacedOnBothSidesInverted(domino) ) {
            char side = readSideFromUser();
            if ( side == 'L' ) {
                table.placeDominoOnLeftInverted(domino);
            } else { // side == 'R
                table.placeDominoOnRightInverted(domino);
            }
        } else if ( table.dominoCanBePlacedOnLeft(domino) ) {
            table.placeDominoOnLeft(domino);
        } else if ( table.dominoCanBePlacedOnLeftInverted(domino) ) {
            table.placeDominoOnLeftInverted(domino);
        } else if ( table.dominoCanBePlacedOnRight(domino) ) {
            table.placeDominoOnRight(domino);
        } else if ( table.dominoCanBePlacedOnRightInverted(domino) ) {
            table.placeDominoOnRightInverted(domino);
        } else {
            dominoWasPlaced = false;
        }
        return dominoWasPlaced;
    }

    protected void removeDominoFromHand(Domino domino) {
        this.hand.remove(domino);
        this.numDominoesOnHand--;
    }

}
