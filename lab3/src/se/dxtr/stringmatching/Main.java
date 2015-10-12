package se.dxtr.stringmatching;

import se.dxtr.stringlibrary.Kattio;
import se.dxtr.stringlibrary.KnuthMorrisPratt;

import java.util.List;

public class Main {

    static Kattio io = new Kattio (System.in, System.out);
    public static void main(String[] args) {
        while (io.hasMoreTokens ()) {
            String pattern = io.getWord ();
            String text = io.getWord ();
            List<Integer> matchIndices = KnuthMorrisPratt.findMatches (pattern, text);
            matchIndices.forEach (i -> io.print (i + " "));
            io.println ();
        }
        io.close ();
    }
}
