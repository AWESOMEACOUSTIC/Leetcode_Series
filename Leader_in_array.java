import java.util.*;
public class Leader_in_array {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the length of the array: ");
        int n = input.nextInt();

        if(n == 0){
            System.out.println("There is no leader in an array");
            return;
        }

        int[] arr = new int[n];

        for(int i =0; i<arr.length; i++){
            arr[i] = input.nextInt();
        }

        find_Leaders(arr);
    }
    public static void find_Leaders(int[] arr){
        int n = arr.length;

        if(n==1){
            System.out.println("Leaders in an array are: "+arr[0]);
            return;
        }
        List<Integer> leaders = new ArrayList<>();

        int maxfrom_right = arr[n-1];
        leaders.add(maxfrom_right);

        for(int i=n-2; i>=0; i--){
            if(arr[i]>maxfrom_right){
                maxfrom_right = arr[i];
                leaders.add(maxfrom_right);
            }
        }
        Collections.reverse(leaders);
        System.out.println("Leaders in an array "+leaders);
    }
}
