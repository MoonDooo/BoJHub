import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Sol();
    }
}
class Sol {
    private Buf buf = new Buf();
    char[] N;
    long[] dp;

    public Sol() {
        N = buf.read().toCharArray();
        dp = new long[N.length];
        for (int i = 0; i<N.length; i++){
            int n = N[i]-'0';
            if (0<n&&n<10){
                if (i==0)dp[i]=1;
                else dp[i]+=dp[i-1];
                dp[i]%=1000000;
            }
            if (i==0)continue;
            int no = N[i-1]-'0';
            if (no<=0||10<=no)continue;
            int input = no*10+n;
            if (10<=input&&input<=26){
                if (i==1)dp[1]+=1;
                else dp[i]+=dp[i-2];
                dp[i]%=1000000;
            }

        }
        System.out.println(dp[N.length-1]);
    }
}
class Buf{
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }

    public Integer readInt(){
        return Integer.parseInt(read());
    }

    public Long readLong(){
        return Long.parseLong(read());
    }
}