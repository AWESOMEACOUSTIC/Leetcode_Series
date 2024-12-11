import java.util.Scanner;
// Time complexity - O(n) and Space complexity - O(n)
public class josephus_trap {

    // Recursive function to solve the Josephus problem
    static int trap(int n, int k) {
        // Base case: If there's only one person left, return 0 (index of the last person)
        if (n == 1) {
            return 0;
        }
        // Recursive case: Solve the problem for (n-1) people and map the result to current circle size
        return ((trap(n - 1, k) + k) % n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of people (n) and the step size (k) from the user
        int n = sc.nextInt();  // Total number of people
        int k = sc.nextInt();  // Step size for elimination

        // Print the safe position (1-indexed) by adding 1 to the 0-indexed result
        System.out.println(trap(n, k) + 1);
    }
}
