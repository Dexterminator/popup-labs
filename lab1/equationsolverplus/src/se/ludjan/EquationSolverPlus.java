package se.ludjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ludde on 15-09-22.
 */
public class EquationSolverPlus {
    static final double EPSILON = 1e-10;
    public static double[] solve(double[][] augmentedMatrix, int n){
        double[] solution = new double[n];
        List<Integer> zeroRowIndices = Collections.emptyList();

        for (int i = 0; i < n; i++) {
            boolean singular = true;

            double[] currRow = augmentedMatrix[i];
            if(Math.abs(currRow[i]) <= EPSILON){
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
                if(singular){
                    zeroRowIndices = determineIfInsolvableOrMultiple(augmentedMatrix, n);   // singular
                    if(zeroRowIndices == null)
                        return null;
                }
            }
            if(zeroRowIndices.contains(i))
                continue;
            for (int j = i+1; j < n; j++) {
                if(zeroRowIndices.contains(j))
                    continue;
                double times = augmentedMatrix[j][i]/currRow[i];
                for (int k = 0; k < n + 1; k++) {
                    // Subtract currRow from this row
                    augmentedMatrix[j][k] -= times * currRow[k];
                }
            }
        }
        // Back substitution
        for (int i = n-1; i >= 0; i--) {
            boolean isNAN = false;
            double sum = 0.0;
            for (int j = i+1; j < n; j++) {
                if(augmentedMatrix[i][j] == 0)
                    continue;
                if (Double.isNaN(solution[j])) {
                    isNAN = true;
                    break;
                }

                sum += augmentedMatrix[i][j] * solution[j];

            }
            if (!isNAN) {
                solution[i] = (augmentedMatrix[i][n] - sum) / augmentedMatrix[i][i];
            } else {
                solution[i] = Double.NaN;
            }

        }
        return solution;
    }

    public static List<Integer> determineIfInsolvableOrMultiple(double[][] augmentedMatrix, int n){
        List<Integer> res = new ArrayList<>();
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
                    res.add(i); // Mark this row as a row that can have multiple solutions
                }
            }
        }
        return res;
    }
}
