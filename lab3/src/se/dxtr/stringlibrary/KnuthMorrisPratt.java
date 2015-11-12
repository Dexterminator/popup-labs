package se.dxtr.stringlibrary;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt {
    public static List<Integer> findMatches (String pattern, String text) {
        int[] prefixTable = calculatePrefixTable(pattern);
        return kmpSearch(pattern, text, prefixTable);
    }

    private static int[] calculatePrefixTable (String pattern) {
        char[] chars = pattern.toCharArray();
        int[] prefixTable = new int[pattern.length ()];
        int currMax = 0;
        prefixTable[0]=0;
        int i = 1;
        while(i < pattern.length()){
            if(chars[i] == chars[currMax]){
                currMax++;
                prefixTable[i] = currMax;
                i++;
            } else {
                if(currMax != 0){
                    currMax = prefixTable[currMax-1];
                } else {
                    prefixTable[i] = 0;
                    i++;
                }
            }
        }



        /*
        if(chars.length == 1){
            return new int[]{-1};
        }
        int pos = 2;
        int currCandidateIndex = 0;
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
        */
        return prefixTable;
    }

    private static List<Integer> kmpSearch(String pattern, String text, int[] prefixTable){
        List<Integer> indices = new ArrayList<>();
        int patternPos = 0;
        int textPos = 0;
        char[] patternChars = pattern.toCharArray();
        char[] textChars = text.toCharArray();
        while(textPos < textChars.length){
            if(textChars[textPos] == patternChars[patternPos]){
                patternPos++;
                textPos++;
            }
            if(patternPos == patternChars.length){
                indices.add(textPos-patternPos);
                patternPos = prefixTable[patternPos-1];
            } else if(textPos < textChars.length && textChars[textPos] != patternChars[patternPos]){
                if(patternPos != 0){
                    patternPos = prefixTable[patternPos-1];
                } else {
                    textPos++;
                }
            }
            /*
            if(patternChars[currIndex] == textChars[matchStart+currIndex]){
                if(currIndex == patternChars.length-1) {
                    indices.add(matchStart);
                    matchStart++;
                    currIndex = 0;
                } else {
                    currIndex++;
                }
            } else {
                matchStart += currIndex - prefixTable[currIndex];
                if(prefixTable[currIndex] > -1) {
                    currIndex = prefixTable[currIndex];
                } else {
                    currIndex = 0;
//                    matchStart++;
                }
            }
            */
        }
        return indices;
    }
}
