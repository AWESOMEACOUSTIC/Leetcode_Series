import java.util.Scanner;

public class Knight_tour {
    static int n = 8; // Size of the chessboard (n x n)
    static int x[] = {2, 1, -1, -2, -2, -1, 1, 2}; // Possible moves in the x-direction for a knight
    static int y[] = {1, 2, 2, 1, -1, -2, -2, -1}; // Possible moves in the y-direction for a knight

    // Function to perform the knight's tour
    public static boolean tour(int[][] arr, int count, int r, int c) {
        if (count == (n * n)) { // Base case: all cells are visited
            return true; // Tour is complete
        }
        for (int i = 0; i < n; i++) { // Check all possible knight moves
            int x1 = x[i] + r; // New x-coordinate
            int y1 = y[i] + c; // New y-coordinate
            if (x1 >= 0 && y1 >= 0 && x1 < n && y1 < n && arr[x1][y1] == 0) {
                // Ensure the move is within bounds and the cell is not visited
                arr[x1][y1] = count; // Mark the cell as visited with the current count
                if (tour(arr, count + 1, x1, y1)) { // Recursive call for the next step
                    return true; // Return true if a solution is found
                }
                arr[x1][y1] = 0; // Backtrack if the move leads to no solution
            }
        }
        return false; // Return false if no valid moves are found
    }

    // Function to print the chessboard
    static void print(int[][] arr) {
        for (int i = 0; i < n; i++) { // Iterate through each row
            for (int j = 0; j < n; j++) { // Iterate through each column
                System.out.print(arr[i][j] + " "); // Print the value of the cell
            }
            System.out.println(); // Print a new line after each row
        }
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Initialize a scanner for user input
        int arr[][] = new int[n][n]; // Create an n x n chessboard
        arr[0][0] = 0; // Start the knight's tour from the top-left corner
        if (tour(arr, 1, 0, 0)) { // Call the tour function with the initial position
            print(arr); // Print the chessboard if a solution is found
        }
        sc.close(); // Close the scanner
    }
}
