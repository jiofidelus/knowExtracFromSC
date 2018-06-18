package tictactoe;

/**
 * An enumeration for the tic-tac-toe markers (X and O)
 * @author mafudge
 */
public enum Marker {
    X ('X'), O('O'), EMPTY(' ');

    private char ch;

    Marker(char ch) {
        this.ch = ch;
    }

    @Override
    public String toString() {
        return String.format("%c", this.ch);
    }
}
