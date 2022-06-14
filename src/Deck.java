import java.util.Collections;
import java.util.Stack;

public class Deck {

    // Object Attributes
    Stack<Domino> dominoes = new Stack<>();

    // Constructor
    public Deck() {
        generateDominoes_();
    }

    // Public Methods
    public Domino drawDomino() {
        return this.dominoes.pop();
    }
    public boolean isEmpty() { return this.dominoes.isEmpty(); }

    // Private Methods
    private void generateDominoes_() {
        for ( byte l=0; l<=Domino.maxValue; l++ ) {
            for ( byte r=0; r<=l; r++) {
                dominoes.push(new Domino(l,r));
            }
        }
        Collections.shuffle(this.dominoes);
    }


}
