public class Domino {

    // Class Attribute
    public static final byte maxValue = 6;

    // Object Attributes
    private int leftValue_;
    private int rightValue_;

    // Constructor
    public Domino(int leftValue, int rightValue) {
        this.leftValue_ = leftValue;
        this.rightValue_ = rightValue;
    }

    // Getters
    public int getLeftValue() { return leftValue_; }
    public int getRightValue() { return rightValue_; }

    // Public Methods
    public void invertValues() {
        int aux = this.leftValue_;
        this.leftValue_ = this.rightValue_;
        this.rightValue_ = aux;
    }

    public String renderToString() {
        return String.format("[%d|%d]", this.leftValue_, this.rightValue_);
    }
}
