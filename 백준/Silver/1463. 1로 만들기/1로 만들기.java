import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) {
        new Sol();
    }
}
class Sol{
    private Buf buf = new Buf();
    int N;
    int[] dp;
    public Sol(){
        N = buf.readInt();
        dp = new int[1000001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1]=0;
        for (int i = 2; i<1000001; i++){
            if (i %3==0){
                dp[i] = Math.min(dp[i],dp[i /3]+1);
            }
            if (i %2==0){
                dp[i] = Math.min(dp[i],dp[i /2]+1);
            }
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }
        System.out.println(dp[N]);
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
}