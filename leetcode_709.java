//Converting the uppercase character in a sting into lowercase
import java.util.*;
class leetcode_709 {
    public static String tolowercase(String s){
        char[] c = s.toCharArray();                     //String to Character = stringname.toCharArray()
        for(int i =0; i<c.length; i++){                 // c.length is used when character array length is required
            if(c[i]>=65 && c[i]<=90){                   // c.length() is used when string of arrays length is required
                c[i] = (char) (c[i] + 32);
            }
        }
        return String.valueOf(c);                     // Charater to String = String.valueof(character name)
    }

    public static void main(String[] args) {
        System.out.print("Enter a string: ");
        Scanner input = new Scanner(System.in);
        String take = input.next();
        String got = tolowercase(take);
        System.out.println(got);
    }
}
