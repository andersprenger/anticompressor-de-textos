package trabalho;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;

import static trabalho.Letter.alphabet;
import static trabalho.Letter.findLetter;

public class AntiCompactor {

    public AntiCompactor(String fileName) {
        alphabet = new LinkedList<>();
        load(fileName);
        findContained();

//        for (Letter l : alphabet) {
//            System.out.println(l.description());
//        }

        System.out.print(fileName + " ");
        for (Letter l : alphabet) {
            if (l.getContained().isEmpty() && !l.getContains().isEmpty()) {
                System.out.print(l);
            }
        }
        System.out.println();
    }

    private void load(String fileName) {
        Path path = Path.of(fileName).toAbsolutePath();
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {

            while (sc.hasNext()) {
                String[] line = sc.nextLine().split(" ");

                switch (line.length) {
                    case 2 -> {
                        char l = line[0].charAt(0);
                        String subs = line[1];
                        Letter letter = new Letter(l, subs);
                        alphabet.add(letter);
                    }

                    case 1 -> {
                        char l = line[0].charAt(0);
                        Letter letter = new Letter(l, null);
                        alphabet.add(letter);
                    }
                }
            }

        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    private void findContained() {
        for (Letter l : alphabet) {
            String subs = l.getSubstitute();
            if (subs == null) {
                continue;
            }

            for (int i = 0; i < subs.length(); i++) {
                char c = subs.charAt(i);
                Letter ct = findLetter(c);
                if (!l.getContains().contains(ct)) {
                    l.addContains(ct);
                }
                if (!ct.getContained().contains(l)) {
                    ct.addContained(l);
                }
            }
        }
    }
}
