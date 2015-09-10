package se.dxtr;
import static se.dxtr.LIS.*;
public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio (System.in, System.out);
        while (io.hasMoreTokens ()) {
            int length = io.getInt ();
            Integer[] sequence = new Integer[length];
            for (int i = 0; i < length; i++)
                sequence[i] = io.getInt ();

            int[] lis = lis (sequence);
            io.println (lis.length);
            for (int index : lis) {
                io.print (index + " ");
            }

            io.println ();
        }
        io.flush ();
        io.close();
    }
}
