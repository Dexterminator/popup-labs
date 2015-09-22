package se.ludjan;

import java.util.Arrays;

/**
 * Created by Ludde on 15-09-22.
 */
public class EquationSolver {
    static final double EPSILON = 1e-10;
    public static double[] solve(double[][] augmentedMatrix, int n){
        double[] solution = new double[n];
        for (int i = 0; i < n; i++) {
            double[] currRow = augmentedMatrix[i];
            if(Math.abs(currRow[i]) <= EPSILON){
                boolean singular = true;
                for (int k = i+1; k < n; k++) {
                    if(Math.abs(augmentedMatrix[k][i]) > EPSILON){
                        // Try to disprove singularity, if so pivot these rows
                        singular = false;
                        double[] tempRow = augmentedMatrix[k];
                        augmentedMatrix[k] = currRow;
                        augmentedMatrix[i] = tempRow;
                        currRow = tempRow;
                        break;
                    }
                }
                if(singular)
                    return determineIfInsolvableOrMultiple(augmentedMatrix, n);   // singular
            }
            for (int j = i+1; j < n; j++) {
//                if(j == i)
//                    continue;
//                if(augmentedMatrix[j][i] == 0)
//                    continue;
                double times = augmentedMatrix[j][i]/currRow[i];
                for (int k = 0; k < n + 1; k++) {
                    // Subtract currRow from this row
                    augmentedMatrix[j][k] -= times * currRow[k];
                }
            }
        }
        // Back substitution
        for (int i = n-1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i+1; j < n; j++) {
                sum += augmentedMatrix[i][j] * solution[j];
            }
            solution[i] = (augmentedMatrix[i][n] - sum) / augmentedMatrix[i][i];
        }
        return solution;
    }

    public static double[] determineIfInsolvableOrMultiple(double[][] augmentedMatrix, int n){
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            boolean rowZero = true;
            for (int j = 0; j < n; j++) {
                if(Math.abs(augmentedMatrix[i][j]) < EPSILON){
                    continue;
                } else {
                    rowZero = false;
                }
            }
            if(rowZero){
                if (Math.abs(augmentedMatrix[i][n]) > EPSILON){
                    return null;
                } else {
                    return new double[]{Double.NaN};
                }
            }
        }
        return res;
    }
}
