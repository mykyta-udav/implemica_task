import java.util.Scanner;

/**
 * This program calculates the number of valid bracket expressions
 * with N opening and N closing brackets. It uses the concept of
 * Catalan numbers to calculate the result.
 * A valid bracket expression is one in which every opening bracket
 * has a corresponding closing bracket, and they are correctly nested.
 */
public class CorrectBracketExpressions {

    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to input the number of pairs of brackets
        System.out.print("Enter the number of opening/closing brackets (N): ");
        int N = scanner.nextInt();

        // Validate the input to ensure it's a non-negative integer
        if (N < 0) {
            System.out.println("N must be a non-negative integer.");
            return; // Exit if the input is invalid
        }

        // Calculate and print the number of valid bracket expressions
        int result = calculateCatalanNumber(N);
        System.out.println("Number of correct bracket expressions: " + result);
    }

    /**
     * Calculates the nth Catalan number using dynamic programming.
     * The Catalan number represents the count of valid bracket
     * expressions for a given number of opening and closing brackets.
     * Catalan number can be computed recursively using the following formula:
     * C(n) = Σ (C(i) * C(n-i-1)) for i = 0 to n-1
     *
     * @param n the number of pairs of brackets (N)
     * @return the nth Catalan number which represents the number of valid bracket expressions
     */
    private static int calculateCatalanNumber(int n) {
        // Base case: the number of valid expressions for 0 pairs of brackets is 1
        if (n == 0) {
            return 1; // C(0) = 1
        }

        // Create an array to store the calculated Catalan numbers
        int[] catalan = new int[n + 1];
        catalan[0] = 1; // C(0) is 1 by definition

        // Loop through numbers from 1 to n to calculate the Catalan numbers iteratively
        for (int i = 1; i <= n; i++) {
            // For each i, calculate the Catalan number C(i) based on the recursive formula
            for (int j = 0; j < i; j++) {
                // The formula C(i) = Σ (C(j) * C(i-j-1)) is applied here
                catalan[i] += catalan[j] * catalan[i - j - 1];
            }
        }

        // The final result for C(n) is stored in catalan[n]
        return catalan[n];
    }
}