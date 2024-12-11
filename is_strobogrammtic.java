import java.util.*;

public class is_strobogrammtic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner for user input

        // Prompt user to enter a number
        System.out.print("Enter a number to check if it's strobogrammatic: ");
        String n = sc.nextLine(); // Read the input as a string

        // Check if the input is valid and process
        if (is_Strobogrammatic(n)) {
            System.out.println("The number is strobogrammatic.");
        } else {
            System.out.println("The number is not strobogrammatic.");
        }
    }

    public static boolean is_Strobogrammatic(String n) {
        // Handle edge cases: null or empty string
        if (n == null || n.isEmpty()) {
            return false; // An empty or null string cannot be strobogrammatic
        }

        // Initialize a map for strobogrammatic digit pairs
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0'); // 0 rotates to 0
        map.put('1', '1'); // 1 rotates to 1
        map.put('8', '8'); // 8 rotates to 8
        map.put('6', '9'); // 6 rotates to 9
        map.put('9', '6'); // 9 rotates to 6

        int left = 0; // Pointer to the left side of the string
        int right = n.length() - 1; // Pointer to the right side of the string

        // Iterate while the left pointer is less than or equal to the right pointer
        while (left <= right) {
            char leftChar = n.charAt(left); // Character at the left pointer
            char rightChar = n.charAt(right); // Character at the right pointer

            // If the right character is not in the map or doesn't match the mapped left character
            if (!map.containsKey(rightChar) || leftChar != map.get(rightChar)) {
                return false; // The number is not strobogrammatic
            }

            // Move the pointers closer
            left++;
            right--;
        }

        // If the loop completes, the number is strobogrammatic
        return true;
    }
}
