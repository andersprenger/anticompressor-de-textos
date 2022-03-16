package trabalho;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static trabalho.Letter.alphabet;

public class AntiCompactor {


    public AntiCompactor() {
        load("caso01");
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
                        System.out.println(letter);
                    }

                    case 1 -> {
                        char l = line[0].charAt(0);
                        Letter letter = new Letter(l, null);
                        alphabet.add(letter);
                        System.out.println(letter);
                    }
                }
            }

        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }
}
