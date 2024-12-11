import java.util.*;

public class is_strobo_inrange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Prompt the user to enter the range
        System.out.print("Enter the starting number of the range: ");
        int start = sc.nextInt();
        System.out.print("Enter the ending number of the range: ");
        int end = sc.nextInt();

        // Call the function to find strobogrammatic numbers within the range
        List<Integer> result = findStrobogrammaticInRange(start, end);

        // Output the result
        if (result.isEmpty()) {
            System.out.println("No strobogrammatic numbers found in the given range.");
        } else {
            System.out.println("Strobogrammatic numbers in the range:");
            for (int num : result) {
                System.out.print(num + " ");
            }
        }
    }

    // Function to find strobogrammatic numbers in a given range
    public static List<Integer> findStrobogrammaticInRange(int start, int end) {
        ArrayList<Integer> strobogrammaticNumbers = new ArrayList<>();

        // Iterate through all numbers in the range
        for (int i = start; i <= end; i++) {
            // Check if the current number is strobogrammatic
            if (isStrobogrammatic(String.valueOf(i))) {
                strobogrammaticNumbers.add(i); // Add to the list if strobogrammatic
            }
        }
        return strobogrammaticNumbers; // Return the list of strobogrammatic numbers
    }

    // Function to check if a number is strobogrammatic
    public static boolean isStrobogrammatic(String n) {
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
