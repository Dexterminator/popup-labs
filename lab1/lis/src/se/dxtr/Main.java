package se.dxtr;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int length = io.getInt ();
            Integer[] sequence = new Integer[length];
            for (int i = 0; i < length; i++)
                sequence[i] = io.getInt ();
            System.out.println (Arrays.toString (sequence));
        }
    }
}
