public class ComPlayer extends Player {

    // Constructor
    public ComPlayer() {
        super.name = "COM";
    }

    // Public Methods
    @Override
    public void printHand(Deck deck) {
        // Actually, it just displays how many dominoes the computer has
        System.out.println(super.name + " has " + super.numDominoesOnHand + " domino(es) on hand!");
    }

    @Override
    public void makeMovement(Deck deck, Table table) {
        boolean dominoWasPlaced = false;
        // Searches COM's hand for a playable domino
        for ( int i=0; i<this.numDominoesOnHand; i++ ) {
            Domino d = this.hand.get(i);
            dominoWasPlaced = this.placeDominoOnTable(d, table);
            if ( dominoWasPlaced ) {
                System.out.println(this.name + " placed a " + d.renderToString());
                this.removeDominoFromHand(d);
                break;
            }
        }
        // Draw a new domino if it hasn't a playable one and attempts to draw it
        if ( !dominoWasPlaced ) {
            if ( !deck.isEmpty() ) {
                this.drawDominoAndPlay(deck, table);
            } else {
                this.passedLastTurn = true;
            }
        }
    }

    @Override
    protected char readSideFromUser() {
        char side;
        // Side will be chosen at random according to whether current millis is even or odd
        if ( System.currentTimeMillis() % 2 == 0 ) {
            side = 'L';
        } else {
            side = 'R';
        }
        return side;
    }

}
