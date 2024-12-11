import java.util.*;
class move_hyphen
{
    public static void main (String[] args)
    {
        // Initialize Scanner to read the input
        Scanner sc = new Scanner(System.in);

        // Read the input string
        String s = sc.nextLine();

        // Convert the string to a character array
        char ch[] = s.toCharArray();

        // Initialize n as the last index of the array
        int n = ch.length - 1;

        // Iterate backwards through the string
        for (int i = n; i >= 0; i--) {
            // If the current character is not a hyphen ('-')
            if (ch[i] != '-') {
                // Swap the current character with the character at index n
                char c = ch[n];
                ch[n] = ch[i];
                ch[i] = c;

                // Decrease n to move the position of the last non-hyphen character
                n--;
            }
        }

        // Print the resulting character array as a string
        System.out.println(ch);
    }
}
