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
    public Sol(){
        int n = buf.readInt();
        int r = buf.readInt();
        int c = buf.readInt();
        System.out.println(dfs(n-1, r, c));
    }
    public int dfs(int n, int x, int y){
        if (x==0&&y==0)return 0;
        int pow = (int) Math.pow(2,n);
        return ((2*pow)*pow*(x/pow))+(pow*pow*(y/pow))+dfs(n-1, x%pow, y%pow);
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