import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Formattable;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    Buf buf = new Buf();
    boolean[][] isVisited;
    int[][] dp;
    int[][] map;
    int N,M;
    int[][] d = {{1,0},{-1,0},{0,1},{0,-1}};
    public boolean isNotOver(int x, int y){
        return 0<=x&&0<=y&&x<M&&y<N;
    }
    public Sol(){
        N = buf.readInt();
        M = buf.readInt();
        dp = new int[M][N];
        isVisited = new boolean[M][N];
        map = new int[M][N];
        for (int i = 0 ; i<N; i++){
            for (int j =  0; j<M; j++){
                map[j][i] = buf.readInt();
            }
        }
        dp[M-1][N-1] = 1;
        isVisited[M-1][N-1]=true;

        System.out.println(dfs(0,0));


    }
    public int dfs(int i, int j){
        if (isVisited[i][j]){
            return dp[i][j];
        }
        isVisited[i][j] = true;
        for (int[] idx : d){
            int dx = i + idx[0];
            int dy = j + idx[1];

            if (isNotOver(dx,dy)&&map[dx][dy]<map[i][j]){
                dp[i][j]+=dfs(dx,dy);
            }
        }
        return dp[i][j];
    }
}
class Buf{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
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