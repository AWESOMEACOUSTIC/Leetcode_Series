import java.util.*;
public class swap_two_nibbiles
{
    public static void main(String[] args) {

        Scanner input=new Scanner(System.in);
        int n= input.nextInt();
        System.out.println("Before Nibble Swap  "+n);
        n=(n&0x0F)<<4 | (n&0xF0)>>4;
        System.out.println("After Nibble Swap  "+n);
    }
}
