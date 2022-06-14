import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class Table {

    // Object Attributes
    private LinkedList<Domino> dominoes = new LinkedList<>();
    private Scanner userInput = new Scanner(System.in);

    // Constructor
    public Table() {

    }

    // Public Methods
    public void addDominoToTable(Domino d, String playerClass) {
        if ( this.dominoes.isEmpty() ) {
            this.dominoes.addFirst(d);
        }
        else {
            char side;
            int dominoLeftValue = d.getLeftValue();
            int dominoRightValue = d.getRightValue();
            int tableLeftMostValue = this.dominoes.getFirst().getLeftValue();
            int tableRightMostValue = this.dominoes.getLast().getRightValue();
            if ( dominoLeftValue == tableLeftMostValue && dominoRightValue == tableRightMostValue ) {
                d.invertValues();
                if ( playerClass.equals("UserPlayer") ) {
                    side = readSideFromUser();
                } else { // playerClass == "ComPlayer"
                    side = 'L';
                }
            } else if ( dominoLeftValue == tableRightMostValue && dominoRightValue == tableLeftMostValue ) {
                if ( playerClass.equals("UserPlayer") ) {
                    side = readSideFromUser();
                } else { // playerClass == "ComPlayer"
                    side = 'R';
                }
            } else if ( dominoLeftValue == tableLeftMostValue ) {
                d.invertValues();
                side = 'L';
            } else if ( dominoRightValue == tableLeftMostValue ) {
                side = 'L';
            } else if ( dominoLeftValue == tableRightMostValue ) {
                side = 'R';
            } else if ( dominoRightValue == tableRightMostValue ) {
                d.invertValues();
                side = 'R';
            } else {
                side = '0';
            }

            if ( side == 'L' ) {
                this.dominoes.addFirst(d);
            } else if ( side == 'R' ) {
                this.dominoes.addLast(d);
            }

        }
    }

    public boolean dominoCanBePlaced(Domino d) {
        int dominoLeftValue = d.getLeftValue();
        int dominoRightValue = d.getRightValue();
        int tableLeftMostValue = this.dominoes.getFirst().getLeftValue();
        int tableRightMostValue = this.dominoes.getLast().getRightValue();
        return ( dominoLeftValue == tableLeftMostValue || dominoRightValue == tableRightMostValue || dominoLeftValue == tableRightMostValue || dominoRightValue == tableLeftMostValue );
    }

    public void print() {
        System.out.println("\n");
        this.dominoes.forEach( d -> System.out.print(d.renderToString()));
        System.out.println("\n");
    }

    // Private Methods
    private char readSideFromUser() {
        char side = '0';
        while ( side != 'L' && side != 'R' ) {
            System.out.println("Choose whether place the piece on [L]eft or [R]ight side:");
            side = userInput.next().toUpperCase().charAt(0);
            if ( side != 'L' && side != 'R' ) {
                System.out.println("Invalid Input!");
            }
        }
        return side;
    }


}
