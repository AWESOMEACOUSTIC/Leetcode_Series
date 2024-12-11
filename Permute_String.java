import java.util.*;
public class Permute_String {
    public static void permute(char[] arr, int l, int r, ArrayList<String>store){
        if(l == r){
            store.add(String.valueOf(arr));
        }
        else{
            for(int i = l; i <= r; i++){
                swap(arr,l,i);
                permute(arr,l+1,r,store);
                swap(arr,l,i);
            }
        }
    }

    public static void swap(char[] a,int i, int j){
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        String s = "ABC";
        char a[] = s.toCharArray();
        int n = a.length;
        ArrayList<String> store = new ArrayList<>();
        permute(a,0,n-1,store);
        Collections.sort(store);
        for(String S : store){
            System.out.print(S + " ");
        }
    }
}
