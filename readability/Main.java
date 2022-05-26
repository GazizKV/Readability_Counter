package readability;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final SyllablesCounter syllablesCounter = new SyllablesCounter();

    public static void main(String[] args) {
        String text = readTextFromFile(args[0]);
        int characterCount = text.trim().replaceAll("\\s", "").split("").length;
        int sentencesCount = text.split("[!.?] ").length;
        int wordCount = text.split("[,.!]?\\s+").length;
        int syllables = syllablesCounter.getNumSyllables(text);
        int polysyllables = syllablesCounter.getNumPolysyllables(text);
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentencesCount);
        System.out.println("Characters: " + characterCount);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String inputChoice = readInputLine();
        System.out.print("\n");
        switch (inputChoice) {
            case "ARI":
                ari(characterCount, sentencesCount, wordCount);
                break;
            case "FK":
                fk(syllables, sentencesCount, wordCount);
                break;
            case "SMOG":
                smog(sentencesCount, polysyllables);
                break;
            case "CL":
                cl(characterCount, sentencesCount, wordCount);
                break;
            case "all":
                all(characterCount, sentencesCount, wordCount, syllables, polysyllables);
                break;
            default:
                break;
        }
    }

    private static void all(int characterCount, int sentencesCount, int wordCount, int syllables, int polysyllables) {
        double sum = 0;
        sum += ari(characterCount, sentencesCount, wordCount);
        sum += fk(syllables, sentencesCount, wordCount);
        sum += smog(sentencesCount, polysyllables);
        sum += cl(characterCount, sentencesCount, wordCount);
        System.out.printf("This text should be understood in average by %.2f-year-olds.", sum / 4);
    }

    private static double cl(int characterCount, int sentencesCount, int wordCount) {
        double L = 1.0 * characterCount / wordCount * 100;
        double S = 1.0 * sentencesCount / wordCount * 100;
        double score = 0.0588 * L - 0.296 * S - 15.8;
        int year = 0;
        if (score <= 1) {
            year = 5;
        } else if (score <= 2) {
            year = 6;
        } else if (score <= 3) {
            year = 7;
        } else if (score <= 4) {
            year = 9;
        } else if (score <= 5) {
            year = 10;
        } else if (score <= 6) {
            year = 11;
        } else if (score <= 7) {
            year = 12;
        } else if (score <= 8) {
            year = 13;
        } else if (score <= 9) {
            year = 14;
        } else if (score <= 10) {
            year = 15;
        } else if (score <= 11) {
            year = 16;
        } else if (score <= 12) {
            year = 17;
        } else if (score <= 13) {
            year = 18;
        } else if (score <= 14) {
            year = 24;
        }
        System.out.printf("Coleman–Liau index: %.2f (about %d-year-olds).%n", score, year);
        return year;
    }

    private static double smog(int sentencesCount, int polysyllables) {
        double score = 1.043 * Math.sqrt(polysyllables * (1.0 * 30 / sentencesCount)) + 3.1291;
        int year = 0;
        if (score <= 1) {
            year = 5;
        } else if (score <= 2) {
            year = 6;
        } else if (score <= 3) {
            year = 7;
        } else if (score <= 4) {
            year = 9;
        } else if (score <= 5) {
            year = 10;
        } else if (score <= 6) {
            year = 11;
        } else if (score <= 7) {
            year = 12;
        } else if (score <= 8) {
            year = 13;
        } else if (score <= 9) {
            year = 14;
        } else if (score <= 10) {
            year = 15;
        } else if (score <= 11) {
            year = 16;
        } else if (score <= 12) {
            year = 17;
        } else if (score <= 13) {
            year = 18;
        } else if (score <= 14) {
            year = 24;
        }
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d-year-olds).%n", score, year);
        return year;
    }

    private static double fk(int syllables, int sentencesCount, int wordCount) {
        double score = 0.39 * (1.0 * wordCount / sentencesCount) + 11.8 * (1.0 * syllables / wordCount) - 15.59;
        double readScore = 206.835 - 1.015 * (1.0 * wordCount / sentencesCount) - 84.6 * (1.0 * syllables / wordCount);
        int age = 0;
        if (readScore > 90) {
            age = 10;
        } else if (readScore > 80) {
            age = 11;
        } else if (readScore > 70) {
            age = 12;
        } else if (readScore > 60) {
            age = 13;
        } else if (readScore > 50) {
            age = 16;
        } else if (readScore > 30) {
            age = 19;
        } else if (readScore > 10) {
            age = 25;
        } else if (readScore > 0) {
            age = 30;
        }
        System.out.printf("Flesch-Kincaid readability test: %.2f (about %d-year-olds).%n", score, age);
        return age;
    }

    private static double ari(int characterCount, int sentencesCount, int wordCount) {
        double score = 4.71 * (1.0 * characterCount / wordCount)
                + 0.5 * (1.0 * wordCount / sentencesCount) - 21.43;
        double scoreCeil = Math.ceil(score);
        int year = 0;
        if (scoreCeil <= 1) {
            year = 5;
        } else if (scoreCeil <= 2) {
            year = 6;
        } else if (scoreCeil <= 3) {
            year = 7;
        } else if (scoreCeil <= 4) {
            year = 9;
        } else if (scoreCeil <= 5) {
            year = 10;
        } else if (scoreCeil <= 6) {
            year = 11;
        } else if (scoreCeil <= 7) {
            year = 12;
        } else if (scoreCeil <= 8) {
            year = 13;
        } else if (scoreCeil <= 9) {
            year = 14;
        } else if (scoreCeil <= 10) {
            year = 15;
        } else if (scoreCeil <= 11) {
            year = 16;
        } else if (scoreCeil <= 12) {
            year = 17;
        } else if (scoreCeil <= 13) {
            year = 18;
        } else if (scoreCeil <= 14) {
            year = 24;
        }
        System.out.printf("Automated Readability Index: %.2f (about %d-year-olds).%n", score, year);
        return year;
    }

    public static String readInputLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readTextFromFile(String fileName) {
        try (
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName))
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            while (fileReader.ready()) {
                stringBuilder.append(fileReader.readLine());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
