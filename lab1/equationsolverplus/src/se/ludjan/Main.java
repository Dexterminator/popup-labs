package se.ludjan;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Kattio io = new Kattio(System.in, System.out);
        EquationSolverPlus solver = new EquationSolverPlus();
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
                for(double coefficient: solution){
                    if(Double.isNaN(coefficient))
                        io.print("? ");
                    else
                        io.print(coefficient + " ");
                }
                io.println();
            }
        }
        io.close();
    }
}
