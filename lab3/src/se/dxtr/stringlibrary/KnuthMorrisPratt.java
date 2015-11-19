package se.dxtr.stringlibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to solve the string matching problems using the
 * KMP algorithm.
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class KnuthMorrisPratt {
    public static List<Integer> findMatches (String pattern, String text) {
        // KMP needs a prefix table
        int[] prefixTable = calculatePrefixTable(pattern);
        return kmpSearch(pattern, text, prefixTable);
    }

    /**
     * Precalculates the prefix table needed by KMP.
     * Really calculates a Longest Prefix Suffix table, meaning on position i in prefixTable,
     * we have the number of characters from the beginning match (prefix) match the suffix in a String.
     * @param pattern The pattern to build the prefix table from.
     * @return The prefix table.
     */
    private static int[] calculatePrefixTable (String pattern) {
        char[] chars = pattern.toCharArray();
        int[] prefixTable = new int[pattern.length ()];
        int currMax = 0;
        prefixTable[0]=0;
        int i = 1;
        while(i < pattern.length()){
            if(chars[i] == chars[currMax]){
                // Match occurs
                currMax++;
                prefixTable[i] = currMax;
                i++;
            } else {
                if(currMax != 0){
                    // We have had a match previously in the String, restart search from there.
                    currMax = prefixTable[currMax-1];
                } else {
                    // No match, the LPS is 0
                    prefixTable[i] = 0;
                    i++;
                }
            }
        }

        return prefixTable;
    }

    /**
     * The KMP algorithm; search for <b>pattern</b> in <b>text</b>.
     * Restart using the <b>prefixTable</b> when a match is found and not found.
     * @param pattern The pattern to search for.
     * @param text The text to search in.
     * @param prefixTable The prefix table used to speed up search;
     * @return
     */
    private static List<Integer> kmpSearch(String pattern, String text, int[] prefixTable){
        List<Integer> indices = new ArrayList<>();
        // Two position pointers are used; one for pattern and one for text.
        int patternPos = 0;
        int textPos = 0;
        char[] patternChars = pattern.toCharArray();
        char[] textChars = text.toCharArray();
        while(textPos < textChars.length){
            // If the two positions match, we have the beginning of a (or we are in) possible match
            if(textChars[textPos] == patternChars[patternPos]){
                patternPos++;
                textPos++;
            }
            // If the above has been true for the entire pattern length, we have a match.
            if(patternPos == patternChars.length){
                indices.add(textPos-patternPos);
                // Continue search based on what the prefix table says
                patternPos = prefixTable[patternPos-1];
            } else if(textPos < textChars.length && textChars[textPos] != patternChars[patternPos]){
                /*
                 * If the two positions don't match and we were in a possible match, we start searching again on the
                 * position specified by the prefix table as it means that the characters in pattern on position 0 to prefixTable[patternPos] still match.
                 * If we were not in a possible match, we just keep going in the text.
                 */
                if(patternPos != 0){
                    patternPos = prefixTable[patternPos-1];
                } else {
                    textPos++;
                }
            }
        }
        return indices;
    }
}
