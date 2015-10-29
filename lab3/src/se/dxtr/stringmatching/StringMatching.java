package se.dxtr.stringmatching;

import se.dxtr.stringlibrary.Kattio;
import se.dxtr.stringlibrary.KnuthMorrisPratt;

import java.util.List;
import java.util.Scanner;

public class StringMatching {

    static Kattio io = new Kattio (System.in, System.out);
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        while (s.hasNext()) {
            String pattern = s.nextLine();
            String text = s.nextLine();
            List<Integer> matchIndices = KnuthMorrisPratt.findMatches (pattern, text);
            matchIndices.forEach(i -> io.print(i + " "));
            io.println ();
        }
        io.close ();
    }
}
