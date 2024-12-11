import java.util.Arrays;

public class bubblesort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        // Loop through the array
        for (int i = 0; i < n - 1; i++) {
            // Compare adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap if elements are in the wrong order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example array
        int[] arr = {64, 34, 25, 12, 22, 11, 90};

        // Perform Bubble Sort
        bubbleSort(arr);

        // Print sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
