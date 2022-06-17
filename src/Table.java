import java.util.LinkedList;

public class Table {

    // Object Attributes
    private LinkedList<Domino> dominoes = new LinkedList<>();

    // Constructor
    public Table() {

    }

    // Getters
    public int getLeftmostValue() { return this.dominoes.getFirst().getLeftValue(); }
    public int getRightmostValue() { return this.dominoes.getLast().getRightValue(); }

    // Public Methods
    public boolean isEmpty() { return this.dominoes.isEmpty(); };
    public boolean dominoCanBePlacedOnLeft(Domino d) { return d.getRightValue() == this.getLeftmostValue(); }
    public boolean dominoCanBePlacedOnRight(Domino d) { return d.getLeftValue() == this.getRightmostValue(); }
    public boolean dominoCanBePlacedOnLeftInverted(Domino d) { return d.getLeftValue() == this.getLeftmostValue(); }
    public boolean dominoCanBePlacedOnRightInverted(Domino d) { return d.getRightValue() == this.getRightmostValue(); }
    public boolean dominoCanBePlacedOnBothSides(Domino d) { return this.dominoCanBePlacedOnLeft(d) && this.dominoCanBePlacedOnRight(d); }
    public boolean dominoCanBePlacedOnBothSidesInverted(Domino d) { return this.dominoCanBePlacedOnLeftInverted(d) && this.dominoCanBePlacedOnRightInverted(d); }

    public void placeDominoOnLeft(Domino d) { this.dominoes.addFirst(d); }
    public void placeDominoOnRight(Domino d) { this.dominoes.addLast(d); }
    public void placeDominoOnLeftInverted(Domino d) { d.invertValues(); this.dominoes.addFirst(d); }
    public void placeDominoOnRightInverted(Domino d) { d.invertValues(); this.dominoes.addLast(d); }

    public void printDominoes() {
        System.out.println();
        this.dominoes.forEach( d -> System.out.print(d.renderToString()));
        System.out.println("\n");
    }

}
