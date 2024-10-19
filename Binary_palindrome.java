import java.util.*;

public class Binary_palindrome {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a decimal number: ");
        int num = input.nextInt();

        boolean isbinary = pal_binary(num);

        if(isbinary){
            System.out.println("The number "+num+" is a binary palindrome");
        }
        else{
            System.out.println("The num "+num +" is not binary palindrome");
        }
    }

    public static boolean pal_binary(int num){
        if(num < 0){
            return false;
        }
        String binaryString = Integer.toBinaryString(num);
        int start = 0;
        int end = binaryString.length() - 1;
        while(start < end){
            if(binaryString.charAt(start)!=binaryString.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
