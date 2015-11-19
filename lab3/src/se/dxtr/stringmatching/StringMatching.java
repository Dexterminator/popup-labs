package se.dxtr.stringmatching;

import se.dxtr.stringlibrary.Kattio;
import se.dxtr.stringlibrary.KnuthMorrisPratt;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Solve the stringmatching Kattis problem
 * <p>
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class StringMatching {

    static Kattio io = new Kattio (System.in, System.out);
    static BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
    static String pattern;
    public static void main(String[] args) {
        try {
            while ((pattern = s.readLine()) != null) {
                String text = s.readLine();
                List<Integer> matchIndices = KnuthMorrisPratt.findMatches(pattern, text);
                matchIndices.forEach(i -> io.print(i + " "));
                io.println();
            }
        } catch (IOException e){
            ;
        }
        io.close ();
    }
}
