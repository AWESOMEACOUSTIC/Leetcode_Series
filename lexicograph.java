import java.util.*;

public class lexicograph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lfps(s));
    }

    static String lfps(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        String mid = "", half = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                if (!mid.isEmpty()) return "false"; // More than one odd frequency
                mid = "" + (char) (i + 'a');
            }
            half += String.valueOf((char) (i + 'a')).repeat(freq[i] / 2);
        }
        return half + mid + new StringBuilder(half).reverse();
    }
}
