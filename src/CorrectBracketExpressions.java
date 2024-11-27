import java.util.Scanner;

/**
 * This program calculates the number of correct bracket expressions
 * with N opening and N closing brackets using Catalan numbers.
 */
public class CorrectBracketExpressions {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input N: number of opening and closing brackets
        System.out.print("Enter the number of opening/closing brackets (N): ");
        int N = scanner.nextInt();

        // Validate input
        if (N < 0) {
            System.out.println("N must be a non-negative integer.");
            return;
        }

        // Calculate and display the result
        int result = calculateCatalanNumber(N);
        System.out.println("Number of correct bracket expressions: " + result);
    }

    /**
     * Calculates the nth Catalan number using a dynamic programming approach.
     * Catalan number represents the count of correct bracket expressions for a given N.
     *
     * @param n the number of bracket pairs
     * @return the nth Catalan number
     */
    private static int calculateCatalanNumber(int n) {
        // Base case: C(0) = 1
        if (n == 0) {
            return 1;
        }

        // Dynamic programming table for Catalan numbers
        int[] catalan = new int[n + 1];
        catalan[0] = 1;

        // Compute C(1) to C(n) using the recursive formula:
        // C(n) = Σ (C(i) * C(n-i-1)) for i = 0 to n-1
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - j - 1];
            }
        }

        return catalan[n];
    }
}
