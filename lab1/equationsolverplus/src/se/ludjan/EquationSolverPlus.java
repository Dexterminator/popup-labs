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
     * @return a double array with the solutions to the equation system
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
            for (int j = i+1; j < n; j++) {
//                if(zeroRowIndices.contains(j))
//                    continue;
                if(Math.abs(currRow[i]) < EPSILON)
                    continue;
                double times = augmentedMatrix[j][i]/currRow[i];
                for (int k = 0; k < n + 1; k++) {
                    // Subtract currRow from this row
                    augmentedMatrix[j][k] -= times * currRow[k];
                }
            }
        }
        zeroRowIndices = determineIfInsolvableOrMultiple(augmentedMatrix, n);   // singular
        if(zeroRowIndices == null)
            return null;
//        System.out.println(Arrays.toString(colPerm));
        for (int i = 0; i < n; i++) {
            invColPerm[colPerm[i]] = i; // Get the inverse permutation of colPerm
        }
        /*
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(augmentedMatrix[i]));
        }*/
//        System.out.println("***");
        // Back substitution
        /*for (int i = 0; i < n; i++) {
            solution[i] = augmentedMatrix[invColPerm[i]][n];
        }*/

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
            if (!isNAN) {
                solution[i] = (augmentedMatrix[i][n] - sum) / augmentedMatrix[i][i];
            } else {
                solution[i] = Double.NaN;
            }

        }
        double[] solution2 = new double[n];
        for (int i = 0; i <n ; i++) {
            solution2[i] = solution[invColPerm[i]];
        }
        return solution2;
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