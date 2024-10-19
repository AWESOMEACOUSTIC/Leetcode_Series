import java.util.*;
public class max_equilibrium_sum
{
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++)
            a[i]=in.nextInt();

        System.out.print(eqi(a,n));
    }
    public static int eqi(int a[],int n){
        int max=0,p=0,s=0;

        for(int i=0;i<n;i++)
            s+=a[i];

        for(int i=0;i<n;i++){
            p+=a[i];

            if(p==s)
                max=Math.max(p,max);

            s-=a[i];
        }
        return max;
    }
}
