package readability;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyllablesCounter {

    // Regex function that return a list of items split by the pattern.
    public List<String> getTokens(String pattern, String text)
    {
        ArrayList<String> tokens = new ArrayList<String>();
        Pattern tokSplitter = Pattern.compile(pattern);
        Matcher m = tokSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }

        return tokens;
    }
    // Helper function to count syllables
    public int countSyllables(String word)
    {
        int numSyllables = 0;
        boolean newSyllable = true;
        String vowels = "aeiouy";
        char[] cArray = word.toCharArray();

        for (int i = 0; i < cArray.length; i++) {

            // dealing with lone 'e's and 'e's in the end of the word.
            if (i == cArray.length-1 && Character.toLowerCase(cArray[i]) == 'e' &&
                    newSyllable && numSyllables > 0) {
                numSyllables--;
            }
            // if the syllable's character is a vowel. Then it stops and count as a syllable.
            if (newSyllable && vowels.indexOf(Character.toLowerCase(cArray[i])) >= 0) {
                newSyllable = false;
                numSyllables++;
            }
            // if the current character is NOT a vowel, starts the new syllable.
            else if (vowels.indexOf(Character.toLowerCase(cArray[i])) < 0) {
                newSyllable = true;
            }
        }
        return numSyllables;
    }
    public int getNumSyllables(String text)
    {
        // get a list of words.
        List<String> tokens = List.of(text.split("[.,]?\\s+"));
        int totalSyllables = 0;
        for (String word : tokens) {
            totalSyllables += countSyllables(word);
        }
        return totalSyllables;
    }

    public int getNumPolysyllables(String text) {
        // get a list of words.
        List<String> tokens = getTokens("[a-zA-Z]+", text);
        int totalPolysyllables = 0;
        int next;
        for (String word : tokens) {
            next = countSyllables(word);
            if (next > 2) {
                totalPolysyllables++;
            }
        }
        return totalPolysyllables;
    }
}
