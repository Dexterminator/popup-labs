package se.ludjan;

/**
 * Utility class for solving linear equations.
 *
 * Authors:
 * Dexter Gramfors, Ludvig Jansson
 */
public class EquationSolver {
    static final double EPSILON = 1e-10;
    enum State {INCONSISTENT, MULTIPLE}

    /**
     *
     * @param augmentedMatrix the augmented matrix of equations and unknowns
     * @param n the dimension of the system of equations, i.e the original matrix
     * @return
     */
    public static double[] solve (double[][] augmentedMatrix, int n) {
        for (int i = 0; i < n; i++) {
            double[] currRow = augmentedMatrix[i];
            if (Math.abs (currRow[i]) <= EPSILON) {
                boolean singular = true;
                for (int k = i + 1; k < n; k++) {
                    if (Math.abs (augmentedMatrix[k][i]) > EPSILON) {
                        // Try to disprove singularity, if so pivot these rows
                        singular = false;
                        double[] tempRow = augmentedMatrix[k];
                        augmentedMatrix[k] = currRow;
                        augmentedMatrix[i] = tempRow;
                        currRow = tempRow;
                        break;
                    }
                }
                if (singular)
                    return determineIfInsolvableOrMultiple (augmentedMatrix, n);
            }
            subtractRows (augmentedMatrix, n, i, currRow);
        }

        double[] solution = performBackSubstitution (augmentedMatrix, n);
        return solution;
    }

    /**
     * Subtract the correct multiple of currRow from the rows below it.
     */
    private static void subtractRows (double[][] augmentedMatrix, int n, int i, double[] currRow) {
        for (int j = i + 1; j < n; j++) {
            double times = augmentedMatrix[j][i] / currRow[i];
            for (int k = 0; k < n + 1; k++) {
                // Subtract currRow from this row
                augmentedMatrix[j][k] -= times * currRow[k];
            }
        }
    }

    /**
     * Perform back substitution throughout the matrix, which is now in upper triangular form, thereby
     * producing the solution.
     */
    private static double[] performBackSubstitution (double[][] augmentedMatrix, int n) {
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += augmentedMatrix[i][j] * solution[j];
            }
            solution[i] = (augmentedMatrix[i][n] - sum) / augmentedMatrix[i][i];
        }
        return solution;
    }

    /**
     *
     */
    private static State determineIfInsolvableOrMultiple (double[][] augmentedMatrix, int n) {
        for (int i = 0; i < n; i++) {
            boolean rowZero = true;
            for (int j = 0; j < n; j++) {
                if (Math.abs (augmentedMatrix[i][j]) < EPSILON) {
                    continue;
                } else {
                    rowZero = false;
                }
            }
            if (rowZero) {
                if (Math.abs (augmentedMatrix[i][n]) > EPSILON) {
                    return State.INCONSISTENTt;
                } else {
                    return State.MULTIPLE;
                }
            }
        }
    }
}
