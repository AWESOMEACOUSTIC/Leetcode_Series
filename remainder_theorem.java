import java.util.Scanner;

public class remainder_theorem {
    // Function to calculate GCD of two numbers
    static int gcd(int a, int b) {
        while (b != 0) { // Continue until the remainder is zero
            int temp = b; // Store the divisor
            b = a % b;    // Update the remainder
            a = temp;     // Update the divisor
        }
        return a; // Return the GCD
    }

    // Function to check if all divisors are pairwise coprime
    static boolean areDivisorsCoprime(int[] div) {
        for (int i = 0; i < div.length; i++) { // Iterate through each divisor
            for (int j = i + 1; j < div.length; j++) { // Compare with every other divisor
                if (gcd(div[i], div[j]) != 1) { // Check if the GCD is not 1
                    return false; // If not coprime, return false
                }
            }
        }
        return true; // Return true if all are coprime
    }

    // Function to find the solution to the system of congruences
    static int find_x(int n, int[] div, int[] rem) {
        if (div.length != rem.length || div.length != n) {
            return -1; // Return -1 if input lengths are inconsistent
        }

        for (int d : div) {
            if (d <= 0) {
                return -2; // Return -2 if any divisor is not positive
            }
        }

        if (!areDivisorsCoprime(div)) {
            return -3; // Return -3 if divisors are not pairwise coprime
        }

        int x = 1; // Initialize x to 1 (start of the search)
        int i; // Index variable for iterating through the congruences
        while (true) {
            for (i = 0; i < n; i++) { // Check each congruence
                if (x % div[i] != rem[i]) { // If x does not satisfy the congruence
                    break; // Break the loop and try the next value of x
                }
            }
            if (i == n) { // If x satisfies all congruences
                return x; // Return x as the solution
            }
            x++; // Increment x to test the next value
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a scanner for user input

        int n = scanner.nextInt(); // Read the number of congruences
        int[] div = new int[n]; // Create an array to store divisors
        for (int i = 0; i < n; i++) { // Populate the divisors array
            div[i] = scanner.nextInt();
        }

        int[] rem = new int[n]; // Create an array to store remainders
        for (int i = 0; i < n; i++) { // Populate the remainders array
            rem[i] = scanner.nextInt();
        }

        int result = find_x(n, div, rem); // Call the function to find the solution
        scanner.close(); // Close the scanner to release resources
    }
}
