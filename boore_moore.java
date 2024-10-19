import java.util.Scanner;

public class boore_moore{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int n = input.nextInt();

        if (n == 0) {
            System.out.println("There is no majority element");
            return;
        }

        int[] arr = new int[n];
        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        int candidate = find_candidate(arr);
        boolean isMajority = is_majority(arr, candidate);

        if (isMajority) {
            System.out.println("The majority element in the array is: " + candidate);
        } else {
            System.out.println("No majority element");
        }
    }

    // Step 1: Find the candidate for the majority element
    public static int find_candidate(int[] arr) {
        int candidate = arr[0];
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = arr[i];
                    count = 1;
                }
            }
        }
        return candidate;
    }

    // Step 2: Verify if the candidate is the majority element
    public static boolean is_majority(int[] arr, int candidate) {
        int count = 0;
        for (int num : arr) {
            if (num == candidate) {
                count++;
            }
        }
        return count > arr.length / 2;
    }
}
