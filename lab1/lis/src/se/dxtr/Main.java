package se.dxtr;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int length = io.getInt ();
            Integer[] sequence = new Integer[length];
            for (int i = 0; i < length; i++)
                sequence[i] = io.getInt ();
            System.out.println ("Input sequence: " + Arrays.toString (sequence));
            List<Integer> lis = LIS.lis (sequence);
            System.out.println (lis);
        }
    }
}
