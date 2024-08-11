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
    int[][] dp;
    int N;
    int[][] input;
    public Sol() {
        N = buf.readInt();
        input = new int [N][N];
        dp = new int[N][1<<N];
        for (int i =0; i<N; i++){
            for (int j =0; j<N; j++){
                input[i][j] = buf.readInt();
            }
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0,1));
    }

    public int dfs(int x, int w) {
        if(w == (1<<N)-1) {
            if(input[x][0] == 0) return 17000000;
            else return input[x][0];
        }
        if(dp[x][w] != -1) return dp[x][w];
        dp[x][w] = 17000000;
        for(int i=0; i<N; i++) {
            if ((w&(1<<i))!=0)continue;
            int next = w | (1<<i);
            if (input[x][i] ==0) continue;
            dp[x][w] = Math.min(dp[x][w], dfs(i, next) + input[x][i]);
        }

        return dp[x][w];
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