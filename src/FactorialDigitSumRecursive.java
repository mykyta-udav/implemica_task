import java.math.BigInteger;

public class FactorialDigitSumRecursive {
    public static void main(String[] args) {
        // Calculate the factorial of 100 using recursion
        BigInteger factorial = calculateFactorial(100);

        // Calculate the sum of the digits of the calculated factorial
        int digitSum = sumOfDigits(factorial);

        // Print the result to the console
        System.out.println("The sum of the digits in 100! is: " + digitSum);
    }

    /**
     * Recursive method to calculate the factorial of a given number.
     *
     * @param n The number for which the factorial needs to be calculated.
     * @return The factorial of the number as a BigInteger.
     */
    private static BigInteger calculateFactorial(int n) {
        // Base case: factorial of 0 or 1 is 1
        if (n == 0 || n == 1) {
            return BigInteger.ONE;
        }
        // Recursive case: n! = n * (n-1)!
        return BigInteger.valueOf(n).multiply(calculateFactorial(n - 1));
    }

    /**
     * Recursive method to calculate the sum of the digits of a given BigInteger.
     *
     * @param number The BigInteger whose digits need to be summed.
     * @return The sum of the digits as an integer.
     */
    private static int sumOfDigits(BigInteger number) {
        // Convert the BigInteger to a string to process each digit
        String numberString = number.toString();

        // Base case: if the number has only one digit, return its integer value
        if (numberString.length() == 1) {
            return Integer.parseInt(numberString);
        }

        // Extract the last digit of the number (rightmost character in the string)
        int lastDigit = Character.getNumericValue(numberString.charAt(numberString.length() - 1));

        // Remove the last digit from the number by creating a new BigInteger from the substring (all but the last digit)
        BigInteger remainingNumber = new BigInteger(numberString.substring(0, numberString.length() - 1));

        // Recursive case: sum the last digit with the sum of the digits of the remaining number
        return lastDigit + sumOfDigits(remainingNumber);
    }
}
