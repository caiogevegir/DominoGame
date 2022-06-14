import java.util.ArrayList;

public abstract class Player {

    // Object Attributes
    protected String name;
    protected ArrayList<Domino> hand = new ArrayList<>();
    protected int numDominoesOnHand = 0;

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
        this.hand.add(deck.drawDomino());
        this.numDominoesOnHand++;
    }

    public int searchDominoOnHand(Domino targetDomino) {
        int index;
        int targetLeftValue = targetDomino.getLeftValue();
        int targetRightValue = targetDomino.getRightValue();
        for (index=this.numDominoesOnHand -1; index>=0; index-- ) {
            Domino d = this.hand.get(index);
            int currentLeftValue = d.getLeftValue();
            int currentRightValue = d.getRightValue();
            if ( (currentLeftValue == targetLeftValue && currentRightValue == targetRightValue) || (currentLeftValue == targetRightValue && currentRightValue == targetLeftValue) ) {
                break;
            }
        }
        return index;
    }

    public void placeDominoOnTable(int dominoIndex, Table table) {
        Domino domino = this.hand.get(dominoIndex);
        table.addDominoToTable(domino, this.getClass().getName());
        System.out.println(this.name + " played a " + domino.renderToString());
        this.hand.remove(dominoIndex);
        this.numDominoesOnHand--;
    }

    public void drawCardsUntilPlay(Deck deck, Table table) {
        while ( !this.hasPlayableDominoOnHand(table) && !deck.isEmpty() ) {
            this.drawDominoFromDeck(deck);
        }
        if ( this.hasPlayableDominoOnHand(table) ) {
            // Last domino is playable
            this.placeDominoOnTable(this.numDominoesOnHand-1, table);
        }
    }

    public abstract void printHand();
    public abstract void makeMovement(Deck deck, Table table);

    // Private Methods
    protected boolean hasPlayableDominoOnHand(Table table) {
        boolean ret = false;
        for ( Domino d : this.hand ) {
            if ( table.dominoCanBePlaced(d) ) {
                ret = true;
                break;
            }
        }
        return ret;
    }

}
