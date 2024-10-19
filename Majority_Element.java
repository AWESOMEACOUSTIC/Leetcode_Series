import java.util.Arrays;
import java.util.Scanner;

public class Majority_Element {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the array length: ");
        int n = input.nextInt();
        int[] arr = new int[n];
        for(int i =0; i<n; i++){
            arr[i] = input.nextInt();
        }
        System.out.println(Arrays.toString(arr));
        int occ = brute_force(n,arr);
        System.out.println("Majority Element is: "+occ);
    }
    public static int brute_force(int n, int[] arr){
        int i =0;
        int count=0;
        for (i =0; i<n; i++){
            count=0;
            for (int j =i+1; j<n; j++){
                if(arr[i]==arr[j]){
                    count++;
                }
            }
        }
        return count>n/2?arr[i]:-1;
    }
}
