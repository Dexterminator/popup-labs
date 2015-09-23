package se.ludjan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ludvig Jansson and Dexter Gramfors on 15-09-22.
 */
public class EquationSolverPlus {
    static final double EPSILON = 1e-10;


    /**
     * EquationSolverPlus.solve() solves a set of linear equations
     * organized in an Augmented Matrix of size n x n
     * @param augmentedMatrix The set of linear equations
     * @param n The size of the matrix
     * @return a double array with the solutions to the equation system, can include NaN's
     */
    public static double[] solve(double[][] augmentedMatrix, int n){
        double[] solution = new double[n];
        List<Integer> zeroRowIndices = Collections.emptyList();
        int[] colPerm = new int[n];   // Array which will contain permutations of columns when swapped
        int[] invColPerm = new int[n]; // This will be the inverse permutation of colPerm
        for (int i = 0; i < n; i++) {
            colPerm[i] = i; // In the beginning, each column is where it is supposed to be
        }
        /* Gaussian elimination */
        for (int i = 0; i < n; i++) {
            /* Find best augmentedMatrix[k][j]; k,j >= i s.t. it is the max value */
            double bestVal = augmentedMatrix[i][i];
            int bestK = i;
            int bestJ = i;
            for (int k = i; k < n; k++) {
                for (int j = i; j < n; j++) {
                    if(Math.abs(augmentedMatrix[k][j]) > bestVal){
                        bestVal = Math.abs(augmentedMatrix[k][j]);
                        bestK = k;
                        bestJ = j;
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                /* Pivot columns when the best j value is found*/
                double temp = augmentedMatrix[j][i];
                augmentedMatrix[j][i] = augmentedMatrix[j][bestJ];
                augmentedMatrix[j][bestJ] = temp;
            }

            /* Update the permutation array*/
            int temp = colPerm[i];
            colPerm[i] = bestJ;
            colPerm[bestJ] = temp;

            /* Pivot rows */
            double[] tempRow = augmentedMatrix[bestK];
            augmentedMatrix[bestK] = augmentedMatrix[i];
            augmentedMatrix[i] = tempRow;

            double[] currRow = augmentedMatrix[i];
            /* Subtract this row from each other row below this one per Gaussian Elimination */
            for (int j = i+1; j < n; j++) {
                if(Math.abs(currRow[i]) < EPSILON)
                    continue;  // We do not want to divide by zero
                double times = augmentedMatrix[j][i]/currRow[i];
                for (int k = 0; k < n + 1; k++) {
                    // Subtract currRow from this row
                    augmentedMatrix[j][k] -= times * currRow[k];
                }
            }
        }
        /* zeroRowIndices is null if no solutions exist or is a list of zero rows if multiple solutions exit*/
        zeroRowIndices = determineIfInsolvableOrMultiple(augmentedMatrix, n);
        if(zeroRowIndices == null)
            return null;
        /* Now that we have the permutation array we create the inverse permutation array */
        for (int i = 0; i < n; i++) {
            invColPerm[colPerm[i]] = i;
        }

        /*
        * Perform backwards substitution as normal.
        * The solutions array will not be in correct order, however
        */
        for (int i = n-1; i >= 0; i--) {
            boolean isNAN = false;
            double sum = 0.0;
            for (int j = i+1; j < n; j++) {
                if(Math.abs(augmentedMatrix[i][j]) < EPSILON)
                    continue;
                if (Double.isNaN(solution[j])) {
                    isNAN = true;
                    break;
                }
                sum += augmentedMatrix[i][j] * solution[j];
            }
            /* NaN in this case means we cannot solve this coefficient.*/
            if (!isNAN) {
                solution[i] = (augmentedMatrix[i][n] - sum) / augmentedMatrix[i][i];
            } else {
                solution[i] = Double.NaN;
            }

        }
        double[] realSolution = new double[n];
        /* Go through the permutated array of solutions and put these on the right
        * spot with the inverse column permutation array */
        for (int i = 0; i <n ; i++) {
            realSolution[i] = solution[invColPerm[i]];
        }
        return realSolution;
    }

    /**
     * Takes a Gaussian Eliminated Augmented Matrix and decides if this can be solved or not. If it can be solved,
     * it returns a list of zero rows, or and empty list if there are none
     * @param augmentedMatrix the matrix of size n x n
     * @param n Size of the matrix
     * @return List of zero rows, null if matrix is inconsistent
     */
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
                    return null; // Found something along the lines of 0 = 1
                } else {
                    res.add(i); // Empty row, add to list
                }
            }
        }
        return res;
    }
}