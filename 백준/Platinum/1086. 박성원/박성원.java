import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args){
        new Sol();
    }
}
class Sol{
    private final Buf buf = new Buf();
    private int N;
    private int K;
    private String[] input;
    private List<Integer> modTen = new ArrayList<>();
    private int[] modNum;
    private long[][] dp;
    public Sol() {
        N = buf.readInt();
        input = new String[N];
        for (int i = 0; i<N; i++){
            input[i] = buf.read();
        }
        K = buf.readInt();
        modNum = new int[N];
        for (int i = 0; i<N; i++){
            modNum[i] = modNum(input[i]);
        }

        int tmp = 1;
        for (int i = 0; i< 50; i++){
            tmp *= 10;
            tmp %= K;
            modTen.add(tmp);
        }
        dp = new long[2<<N][K];
        dp[0][0] = 1;
        for (int i = 0; i< 1<<N; i++){
            for (int j = 0; j<N; j++){
                if ((i & 1<<j)!=0)continue;
                int next = i|(1<<j);
                for (int k = 0; k<K; k++){
                    dp[next][(modTen.get(input[j].length()-1)*k + modNum[j])%K] += dp[i][k];
                }
            }
        }

        long mit = 1;
        for (int i =1; i<=N; i++){
            mit *= i;
        }
        long cin = dp[(1<<N)-1][0];
        long gcd = gcd(mit, cin);
        System.out.println((cin/gcd)+"/"+(mit/gcd));
    }

    public long gcd(long a, long b){
        if (b==0)return a;
        else return gcd(b, a%b);
    }
    public int modNum(String s){
        int tmp = 0;
        for (int i = 0; i<s.length(); i++){
            tmp *= 10;
            tmp += s.charAt(i) -'0';
            tmp %= K;
        }
        return tmp;
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