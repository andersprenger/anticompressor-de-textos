package trabalho;

import java.util.LinkedList;
import java.util.List;

public class Letter {
    static List<Letter> alphabet = new LinkedList<>();
    private Character character;
    private String substitute;

    private List<Letter> contained;
    private List<Letter> contains;

    public Letter(Character character, String substitute) {
        this.character = character;
        this.substitute = substitute;

        this.contained = new LinkedList<>();
        this.contains = new LinkedList<>();
    }

    public Character getCharacter() {
        return character;
    }

    public String getSubstitute() {
        return substitute;
    }

    public List<Letter> getContained() {
        return contained;
    }

    public List<Letter> getContains() {
        return contains;
    }

    public void addContained(Letter l) {
        this.contained.add(l);
    }

    public void addContains(Letter l) {
        this.contains.add(l);
    }

    @Override
    public String toString() {
        return "Letter{" +
                "character=" + character +
                ", substitute=" + substitute +
                '}';
    }
}
