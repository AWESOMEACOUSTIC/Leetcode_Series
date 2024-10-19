import java.util.*;

public class lexicograph
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lfps(s));
    }
    static String lfps(String s){
        int n = s.length();
        int[] freq = new int[26];
        for(int i=0;i<n;i++){
            freq[(s.charAt(i)-'a')]++;
        }
        int odd_count=0;
        for(int i=0;i<26;i++){
            if(freq[i]%2==1){
                odd_count++;
            }
        }
        if(odd_count>1){
            return "false";
        }
        String mid="";
        for(int i=0;i<26;i++){
            if(freq[i]%2==1){
                mid+=(char)(i+'a');
                freq[i]--;
            }
        }
        String front="",rear="",temp="";
        for(int i=0;i<26;i++){
            if(freq[i]%2==0){
                char ch = (char)(i+'a');
                for(int j=0;j<freq[i]/2;j++){
                    temp+=ch;
                }
                front=front+temp;
                rear=temp+rear;
                temp="";

            }
        }
        return front+mid+rear;
    }
}
