import java.util.*;
public class longest_Subsequence
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 15;

        System.out.println(find_seq(n));
    }

    public static int find_seq(int n){
        int cl=0,pl=0,m=0;
        while(n!=0){
            if((n & 1)==1){
                cl++;
            }
            else if((n & 1)==1){
                if((n & 2)==1){
                    pl=0;
                }
                else{
                    pl=cl;
                }
                cl=0;
            }
            m = Math.max(pl+cl+1,m);
            n>>=1;
        }
        return m;
    }
}
