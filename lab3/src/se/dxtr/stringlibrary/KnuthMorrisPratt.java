package se.dxtr.stringlibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnuthMorrisPratt {
    public static List<Integer> findMatches (String pattern, String text) {
//        System.err.println (Arrays.toString (calculatePrefixTable ("ABCDABD")));
        int[] prefixTable = calculatePrefixTable(pattern);
        return kmpSearch(pattern, text, prefixTable);
    }

    private static int[] calculatePrefixTable (String pattern) {
        char[] chars = pattern.toCharArray ();
        if(chars.length == 1){
            return new int[]{-1};
        }
        int pos = 2;
        int currCandidateIndex = 0;
        int[] prefixTable = new int[pattern.length ()];
        prefixTable[0] = -1;
        prefixTable[1] = 0;

        while (pos < pattern.length ()) {
            if (chars[pos - 1] == chars[currCandidateIndex]) {
                currCandidateIndex = currCandidateIndex + 1;
                prefixTable[pos] = currCandidateIndex;
                pos++;
            } else if (currCandidateIndex > 0) {
                currCandidateIndex = prefixTable[currCandidateIndex];
            } else {
                prefixTable[pos] = 0;
                pos++;
            }
        }
        return prefixTable;
    }

    private static List<Integer> kmpSearch(String pattern, String text, int[] prefixTable){
        List<Integer> indices = new ArrayList<>();
        int matchStart = 0;
        int currIndex = 0;
        char[] patternChars = pattern.toCharArray();
        char[] textChars = text.toCharArray();

        while(matchStart + currIndex < textChars.length){
            if(patternChars[currIndex] == textChars[matchStart+currIndex]){
                if(currIndex == patternChars.length-1) {
                    indices.add(matchStart);
                    matchStart++;
                    currIndex = 0;
                } else {
                    currIndex++;
                }
            } else {
                if(prefixTable[currIndex] > -1) {
                    matchStart += currIndex - prefixTable[currIndex];
                    currIndex = prefixTable[currIndex];
                } else {
                    currIndex = 0;
                    matchStart++;
                }
            }
        }
        return indices;
    }
}
