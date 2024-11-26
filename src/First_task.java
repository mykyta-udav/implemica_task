import java.util.Scanner;

public class First_task {
    // Calculate Catalan number for given n
    public static long catalanNumber(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case: C(0) = 1

        for (int i = 1; i <= n; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int n = scanner.nextInt();

        System.out.println("Number of valid bracket expressions: " + catalanNumber(n));
    }
}
