import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) {
        new Sol();
    }
}
class Sol{
    private Buf buf = new Buf();
    int N;
    girum[] girum;
    int M;
    int[] dp;
    public Sol(){
        N = buf.readInt();
        M = buf.readInt();
        girum = new girum[N];
        for (int i = 0; i<N; i++){
            girum[i] = new girum(buf.readInt(), buf.readInt(), buf.readInt());
        }
        dp = new int[100001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i<100001; i++){
            dp[i] = dp[i-1]+1;
            for (int j = 0; j<N; j++){
                if (i==girum[j].getE()){
                    dp[i] = Math.min(girum[j].getTime()+dp[girum[j].getS()], dp[i]);
                }
            }
        }
        System.out.println(dp[M]);
    }
}
class girum{
    private int s;
    private int e;
    private int time;

    public girum(int s, int e, int time) {
        this.s = s;
        this.e = e;
        this.time = time;
    }

    public int getS() {
        return s;
    }

    public int getE() {
        return e;
    }

    public int getTime() {
        return time;
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