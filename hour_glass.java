import java.util.*;

public class hour_glass{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] a = new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0; j<c;j++){
                a[i][j] = sc.nextInt();
            }
        }
        System.out.println(max_hour(r,c,a));
    }
    public static int max_hour(int r, int c, int[][] a){
        int sum =0;
        int max=0;
        if(r<3 || c<3){
            return 0;
        }
        for(int i=0; i<r-2;i++){
            for(int j=0; j<c-2; j++){
                sum = a[i][j]+a[i][j+1]+a[i][j+2]+a[i+1][j+1]+a[i+2][j]+a[i+2][j+1]+a[i+2][j+2];
                max = Math.max(max,sum);
            }
        }
        return max;
    }
}