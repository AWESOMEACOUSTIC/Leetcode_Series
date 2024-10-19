import java.util.Scanner;
public class euclid_algorithm {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number a: ");
        int a = input.nextInt();
        System.out.println("Enter a number b: ");
        int b = input.nextInt();

        int gcd = euclid(a, b);
        System.out.println("The gcd gcd of "+a+" and "+b+ " is "+gcd);
    }

    public static int euclid(int a, int b){

        a = Math.abs(a);
        b = Math.abs(b);
        while(b!=0){
            int remainder = a%b;
            a = b;
            b = remainder;
        }
        return a;
    }
}


