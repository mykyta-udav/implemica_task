import java.math.BigInteger;

public class Third_class {
    public static void main(String[] args) {
        BigInteger factorial = BigInteger.ONE;

        // Calculate 100!
        for (int i = 2; i <= 100; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        // Sum of digits
        String factorialString = factorial.toString();
        int sum = 0;
        for (char digit : factorialString.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }

        System.out.println("Sum of digits in 100! is: " + sum);
    }
}

