import java.util.*;
public class max_product_subarray {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the length of the array: ");
        int num = input.nextInt();
        int[] arr = new int[num];

        for(int i =0; i<num; i++){
            arr[i] = input.nextInt();
        }

        int result = max_product(arr);
        System.out.println("The max product in the array is : "+result);
    }

    public static int max_product(int[] arr){
        int n = arr.length;
        if(arr.length == 0){
            return 0;
        }
        // init the first element of the array as max, min and result
        int maxsofar = arr[0];
        int minsofar = arr[0];
        int result = arr[0];
        for(int i =1; i<arr.length; i++){
            // if the element is negative, swap the maxsofar and minsofar
            if(arr[i]<0){
                int temp = maxsofar;
                maxsofar = minsofar;
                minsofar = temp;
            }
            //update maxsofar and minsofar

            maxsofar = Math.max(arr[i],maxsofar*arr[i]);
            minsofar = Math.min(arr[i],minsofar*arr[i]);

            //update the result
            result = Math.max(result,maxsofar);
        }
        return result;
    }
}
