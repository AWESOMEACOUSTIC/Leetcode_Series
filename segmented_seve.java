import java.util.*;

public class segmented_seve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read input values for the range [l, h]
        int h = sc.nextInt();  // Upper bound of the range
        int l = sc.nextInt();  // Lower bound of the range

        // Handle edge case where l > h
        if (l > h) {
            System.out.println("Invalid range: lower bound is greater than upper bound");
            return;
        }

        // Boolean array to track prime status in the range [l, h]
        boolean[] a = new boolean[h - l + 1];  // Initially assume all numbers are prime

        // If l is 1, mark it as non-prime
        if (l == 1) {
            a[0] = true;  // 1 is not a prime number
        }

        // Loop through all numbers up to sqrt(h) to mark their multiples as non-prime
        for (int i = 2; i < Math.sqrt(h) + 1; i++) {
            // Find the smallest multiple of i in the range [l, h]
            int sm = (l / i) * i;  // Find the smallest multiple of i <= l

            // If the smallest multiple is less than l, adjust it to the next multiple
            if (sm < l) {
                sm += i;
            }

            // Mark all multiples of i as non-prime in the range [l, h]
            for (int j = sm; j <= h; j += i) {
                a[j - l] = true;  // Mark this number as non-prime
            }
        }

        // Output all numbers that are prime in the range [l, h]
        for (int p = 0; p <= h - l; p++) {
            if (!a[p]) {  // If the number is prime
                System.out.print(p + l + " ");  // Print the prime number
            }
        }
    }
}
