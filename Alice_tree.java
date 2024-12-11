import java.util.*;

public class Alice_tree
{
    // Function to find the perimeter based on the required area
    static int find_perimeter(int need) {
        int n = 0;   // The number of layers in the tree
        int sum = 0; // Accumulated area

        // Loop to accumulate area until it exceeds or meets the required need
        while (need > sum) {
            n += 1;          // Increment the number of layers
            sum += 12 * n * n; // Add the area of the current layer (12 * n^2)
        }

        // Return the perimeter, which is 8 * n
        return 8 * n;
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int need = sc.nextInt(); // Input the required area
        System.out.print(find_perimeter(need)); // Output the calculated perimeter
    }
}
