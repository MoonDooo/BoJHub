import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new sol();
    }
}
class sol{
    sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] apl = new int[26];
        for (int i = 0 ; i<s.length(); i++){
            char c = s.charAt(i);
            if ('a'<=c)apl[c-'a']++;
            else apl[c-'A']++;
        }
        int max = 0;
        int idx = 0;
        char c = '?';
        for (int i =0; i<apl.length; i++){
            if (max < apl[i]){
                max = apl[i];
                c = (char) (i+'A');
            }else if (max == apl[i]){
                c = '?';
            }
        }
        System.out.println(c);
    }
}