import java.sql.SQLOutput;
import java.util.Scanner;

public class toggle_switch {
    public static int toggle(){
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) { // Check if input is an integer
            return -1;
        }
        int n = sc.nextInt(); // Read the integer input

        // Handle edge cases
        if (n < 0) { // Negative input
            return -1;
        } else if (n == 0) { // Zero door
            return 0;
        }

        // Calculate the number of open doors (perfect squares ≤ n)
        int result = (int) Math.sqrt(n); // Square root of n gives the count of perfect squares
        return result;
    }
    public static void main(String[] args) {
        int res = toggle();
        System.out.println(res);
    }
}
