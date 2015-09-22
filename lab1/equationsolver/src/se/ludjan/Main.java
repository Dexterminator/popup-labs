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
                break; // No more test cases
            }
            double[][] matrix = new double[n][n+1];
            double[] vector = new double[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = io.getDouble();
                }
            }
            for (int i = 0; i < n; i++) {
                matrix[i][n] = io.getDouble();
            }
            double[] solution = solver.solve(matrix, n);
            if(solution == null)
                io.println("inconsistent");
            else{
                if(Double.isNaN(solution[0])){
                    io.println("multiple");
                } else {
                    for(double coefficient: solution){
                        io.print(coefficient + " ");
                    }
                    io.println();
                }
            }
            /* Debug prints */
            if(debug) {
                System.err.println("Matrix 1:");
                for (int i = 0; i < n; i++) {
                    System.err.println(Arrays.toString(matrix[i]));
                }
                //System.err.println("Vector 1:");
                //System.err.println(Arrays.toString(vector));
                //System.err.println("***");
            }
            k++;
        }
        io.close();
    }
}
