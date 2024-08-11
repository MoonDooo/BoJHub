import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    private Buf buf = new Buf();
    private int result = 0;
    private int[] dx = {1,-1,0,0};
    private int[] dy = {0,0,1,-1};
    boolean[][] isGeChu;
    int M;
    int N;
    int K;
    public Sol(){
        int T = buf.readInt();

        for (int t = 0; t<T; t++){
            M = buf.readInt();
            N = buf.readInt();
            K = buf.readInt();
            isGeChu = new boolean[M][N];
            result = 0;
            for (int i = 0; i<K; i++){
                isGeChu[buf.readInt()][buf.readInt()] = true;
            }
            for(int i = 0; i<M; i++){
                for (int j = 0; j<N; j++){
                    if(isGeChu[i][j]){
                        result++;
                        dfs(i, j);
                    }
                }
            }
            System.out.println(result);
        }
    }

    public void dfs(int x, int y) {
        isGeChu[x][y] = false;
        for(int i = 0; i<4; i++){
            if (0<=dx[i]+x&&dx[i]+x<M&&0<=dy[i]+y&&dy[i]+y<N&&isGeChu[dx[i]+x][dy[i]+y]){
                dfs(dx[i]+x, dy[i]+y);
            }
        }
    }
}
class Buf{
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;
    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }catch (IOException e){
                return null;
            }
        }
        return st.nextToken();
    }
    public Integer readInt(){
        return Integer.parseInt(read());
    }
}