import java.util.*;

public class euler_phi {

    // Function to calculate Euler's Totient Function using brute force
    public static int euler(int n) {
        int res = 0; // Initialize result as 0
        for (int i = 1; i < n; i++) { // Iterate through all numbers less than n
            if (gcd(i, n) == 1) { // Check if the current number is coprime with n
                res ++; // Increment the count if coprime
            }
        }
        return res; // Return the total count of coprime numbers
    }

    // Function to compute GCD (Greatest Common Divisor) of two numbers
    public static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while(b!=0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Read the input number
        if (n <= 0) { // Handle edge case for non-positive integers
            System.out.println("Invalid input. Please enter a positive integer.");
            return;
        }
        System.out.print(euler(n)); // Print the result of Euler's Totient Function
    }
}
