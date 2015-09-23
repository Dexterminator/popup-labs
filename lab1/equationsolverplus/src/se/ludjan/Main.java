package se.ludjan;


public class Main {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        EquationSolverPlus solver = new EquationSolverPlus();
        /*
        * While we have input in the Kattio instance, read values,
        * contruct augmented matrix, and send to solver
        **/
        while(io.hasMoreTokens()){
            int n = io.getInt();
            if(n == 0){
                break; // No more test cases
            }
            double[][] matrix = new double[n][n+1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = io.getDouble();
                }
            }
            for (int i = 0; i < n; i++) {
                matrix[i][n] = io.getDouble();
            }
            double[] solution = solver.solve(matrix, n);
            if(solution == null) // No solution from solver means matrix is inconsistent
                io.println("inconsistent");
            else{
                /* Print results */
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
