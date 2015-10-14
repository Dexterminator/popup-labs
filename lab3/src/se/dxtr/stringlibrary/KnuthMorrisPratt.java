package se.dxtr.stringlibrary;

import java.util.Arrays;
import java.util.List;

public class KnuthMorrisPratt {

    public static List<Integer> findMatches (String pattern, String text) {
        System.err.println (Arrays.toString (calculatePrefixTable ("ABCDABD")));
        return null;
    }

    private static int[] calculatePrefixTable (String pattern) {
        char[] chars = pattern.toCharArray ();
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
}
