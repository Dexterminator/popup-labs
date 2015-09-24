package se.ludjan;

/**
 * Utility class that provides a method for solving partial linear equations.
 *
 * Authors:
 * Ludvig Jansson and Dexter Gramfors
 */
public class EquationSolverPlus {
    static final double EPSILON = 1e-10;
    enum State {INCONSISTENT, MULTIPLE}

    /**
     * Solves a set of linear equation organized in an augmented matrix of size n x n+1
     * @param augmentedMatrix The set of linear equations
     * @param n The size of set of linear equations
     * @return a double array with the solutions to the equation system containing all deductible variables.
     * The indices of the variables which could not be deduced will contain NaN:s.
     */
    public static double[] solve(double[][] augmentedMatrix, int n){
        double[] solution;
        augmentedMatrix = sortMatrix(augmentedMatrix, n);

        boolean multiple = false;
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
                        break;
                    }
                }

                if (singular){
                    // Singularity is found: matrix either has multiple solutions or none
                    State matrixState = determineIfInsolvableOrMultiple (augmentedMatrix, n);
                    if(matrixState == State.INCONSISTENT)
                        return null;
                    else // matrixState == State.MULTIPLE
                        multiple = true;
                }
            }
            subtractCurrentRowFromOtherRows (augmentedMatrix, n, i);
        }

        if(!multiple) {
            // No multiple solutions. Use simple back substitution to find answer
            solution = performBackSubstitution(augmentedMatrix, n);
            return solution;
        }

        // If there are multiple solutions, the easiest way to find these is adding Jordan to Gauss
        augmentedMatrix = sortMatrix(augmentedMatrix, n);
        performGaussJordan (augmentedMatrix, n);

        // Both the Gauss and Gauss-Jordan may have rearranged the rows.
        // Sorting them again gives us values along the diagonal, which we want
        augmentedMatrix = sortMatrix(augmentedMatrix, n);

        return getUnderdeterminedSolution (augmentedMatrix, n);
    }

    /**
     * Returns a double array containing all deductible variables when the system is underdetermined.
     */
    private static double[] getUnderdeterminedSolution (double[][] augmentedMatrix, int n) {
        double[] solution;
        solution = new double[n];
        for (int i = 0; i < n; i++) {
            boolean isZero = true;

            // If there is no value at the diagonal after Gauss-Jordan, we know that the corresponding
            // coefficient has multiple solutions (We have already determined the matrix has a solution)
            if(Math.abs(augmentedMatrix[i][i]) > EPSILON)
                isZero = false;
            if (isZero) {
                solution[i] = Double.NaN;
                continue;
            }
            int coeffCounter = 0;

            // If there are more values != 0 on this row than the diagonal, the coefficient also has multiple solution
            for (int j = i; j < n; j++) {
                if (Math.abs(augmentedMatrix[i][j]) > EPSILON)
                    coeffCounter++;
            }

            if (coeffCounter > 1) {
                solution[i] = Double.NaN;
                continue;
            }
            solution[i] = augmentedMatrix[i][n] / augmentedMatrix[i][i];
        }
        return solution;
    }

    /**
     * Subtract this row from the others the correct number of times
     */
    private static void subtractCurrentRowFromOtherRows (double[][] augmentedMatrix, int n, int i) {
        double[] currRow;
        currRow = augmentedMatrix[i];
        if (Math.abs(currRow[i]) < EPSILON)
            return;
        double times = 1/currRow[i];
        for (int j = i + 1; j < n; j++) {
            double target = augmentedMatrix[j][i];
            for (int k = i; k < n + 1; k++) {
                // Subtract currRow from this row
                augmentedMatrix[j][k] -= times*target*augmentedMatrix[i][k];
            }
        }
    }

    /**
     * Performs Gauss-Jordan by subtracting back up the matrix
     */
    private static void performGaussJordan (double[][] augmentedMatrix, int n) {
        for (int i = n-1; i >=0 ; i--) {
            double[] currRow = augmentedMatrix[i];
            if(Math.abs(currRow[i]) < EPSILON)
                continue;  // We do not want to divide by zero
            double times = 1.0/currRow[i];
            for (int j = i-1; j >= 0 ; j--) {
                double target = augmentedMatrix[j][i];
                for (int k = 0; k < n+1; k++) {
                    augmentedMatrix[j][k] -= times*target*augmentedMatrix[i][k];
                }
            }
        }
    }

    /**
     * Both Gauss and Gauss-Jordan may rearrange the matrix.
     * Sorts the matrix so we, if possible, have values along the diagonal in the matrix.
     */
    private static double[][] sortMatrix(double[][] augmentedMatrix, int n){
        for (int i = 0; i < n; i++) {
            if(Math.abs(augmentedMatrix[i][i]) > EPSILON)
                continue;
            for (int j = i+1; j < n; j++) {
                if(Math.abs(augmentedMatrix[i][j]) >EPSILON){
                    double[] temp = augmentedMatrix[j];
                    augmentedMatrix[j] = augmentedMatrix[i];
                    augmentedMatrix[i] = temp;
                }
            }
        }
        return augmentedMatrix;
    }

    /**
     * Perform back substitution throughout the matrix, which is now in upper triangular form, thereby
     * producing the solution.
     */
    private static double[] performBackSubstitution(double[][] augmentedMatrix, int n) {
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
     * Determine if the linear equation system is inconsistent or contains multiple solutions
     */
    private static State determineIfInsolvableOrMultiple (double[][] augmentedMatrix, int n) {
        State ret = State.MULTIPLE;
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
                    ret = State.INCONSISTENT;
                    break;
                } else {
                    ret = State.MULTIPLE;
                    break;
                }
            }
        }

        return ret;
    }
}
