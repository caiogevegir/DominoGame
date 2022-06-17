import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck {

    // Object Attributes
    private Stack<Domino> dominoes = new Stack<>();
    public ArrayList<Domino> doubles = new ArrayList<>();

    // Constructor
    public Deck() {
        generateDominoes();
    }

    // Getters
    public Domino getDouble(int index) { return this.doubles.get(index); }

    // Public Methods
    public Domino drawDomino() { return this.dominoes.pop(); }
    public boolean isEmpty() { return this.dominoes.isEmpty(); }

    // Private Methods
    private void generateDominoes() {
        for ( byte l=Domino.maxValue; l>=0; l-- ) {
            for ( byte r=l; r>=0; r-- ) {
                Domino d = new Domino(l,r);
                dominoes.push(d);
                if ( l == r ) {
                    doubles.add(d);
                }
            }
        }
        Collections.shuffle(this.dominoes);
    }

}
