import java.util.*;
public class block_swap
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int n = input.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }

        System.out.print("Enter the value of r: ");
        int r = input.nextInt();
        block(arr, r, n);

        System.out.println("Array after block swap:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void swap(int[] arr, int s, int e, int n){
        int temp;
        for(int i=0;i<n;i++){
            temp=arr[e];
            arr[e]=arr[s];
            arr[s]=temp;
            s++;
            e++;
        }
    }
    static void block(int[] arr, int r, int n){
        if(r==0 || r==n){
            return;
        }
        if(r>n){
            r=r%n;
        }
        int a=r;
        int b=n-r;
        while(a!=b){
            if(a<b){
                swap(arr,r-a,a+b-r,a);
                b=b-a;
            }
            else{
                swap(arr,r-a,r,b);
                a=a-b;
            }
        }
        swap(arr,r-a,r,b);
    }
}
