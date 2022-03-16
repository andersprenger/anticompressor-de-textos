package trabalho;

import java.util.LinkedList;
import java.util.List;

public class Letter {
    static List<Letter> alphabet = new LinkedList<>();
    private final Character character;
    private final String substitute;

    private final List<Letter> contained;
    private final List<Letter> contains;

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

    public static Letter findLetter(char c) {
        for (Letter l : alphabet) {
            if (l.getCharacter() == c) {
                return l;
            }
        }

        throw new RuntimeException("Letter " + c + " not found.");
    }

    public String description() {
        return "Letter " + character + '\n' +
               "Substitute: " + substitute + '\n' +
               "Contained by: " + contained + '\n' +
               "Contains these: " + contains + '\n';
    }

    @Override
    public String toString() {
        return character.toString().toUpperCase();
    }
}
