package se.ludjan;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Kattio io = new Kattio(System.in, System.out);
        EquationSolver solver = new EquationSolver();
        int k = 1;
        boolean debug = false;
        if(args.length > 0)
            debug = Boolean.parseBoolean(args[0]);
        while(io.hasMoreTokens()){
            int n = io.getInt();
            if(n == 0){
                io.println("inconsistent");
                break;
            }
            double[][] matrix = new double[n][n];
            double[] vector = new double[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = io.getDouble();
                }
            }
            for (int i = 0; i < n; i++) {
                vector[i] = io.getDouble();
            }
            /* Debug prints */
            if(debug) {
                System.err.println("Matrix 1:");
                for (int i = 0; i < n; i++) {
                    System.err.println(Arrays.toString(matrix[i]));
                }
                System.err.println("Vector 1:");
                System.err.println(Arrays.toString(vector));
                System.err.println("***");
            }
            k++;
        }
        io.close();
    }
}
