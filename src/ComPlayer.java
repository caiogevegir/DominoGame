public class ComPlayer extends Player {

    // Constructor
    public ComPlayer() {
        this.name = "COM";
    }

    // Public Methods
    @Override
    public void printHand() {
        // Actually, just displays how many pieces the computer has
        System.out.println(this.name + " has " + this.numDominoesOnHand + " domino(es) on hand!");
    }

    @Override
    public void makeMovement(Deck deck, Table table) {
        for ( int d=0; d<this.numDominoesOnHand; d++ ) {
            Domino domino = this.hand.get(d);
            if ( table.dominoCanBePlaced(domino) ) {
                this.placeDominoOnTable(d, table);
                break;
            }
        }
    }
}
