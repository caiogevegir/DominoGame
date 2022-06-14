import java.util.Scanner;

public class UserPlayer extends Player {

    // Object Attributes
    private Scanner userInput = new Scanner(System.in);
    // Constructors
    public UserPlayer(String name) {
        super.name = name;
    }

    // Public Methods
    @Override
    public void printHand() {
        hand.forEach((d) -> System.out.print(d.renderToString() + " "));
        System.out.println(); // Skips line after last domino
    }

    @Override
    public void makeMovement(Deck deck, Table table) {
        boolean isPieceValid = false;
        int pieceId;
        Domino domino;
        while ( !isPieceValid ) {
            pieceId = userInput.nextInt();
            if ( pieceId < 0 && pieceId > this.numDominoesOnHand ) {
                System.out.println("Please, choose a valid index [0-" + this.numDominoesOnHand + "]:");
                continue;
            }
            domino = this.hand.get(pieceId);
            if ( table.dominoCanBePlaced(domino) ) {
                this.placeDominoOnTable(pieceId, table);
                isPieceValid = true;
            } else {
                System.out.println("This piece cannot be placed now. Choose another one!");
            }
        }
    }

}
