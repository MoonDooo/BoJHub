import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        new Sol();
    }
}
class Sol{
    private Buf buf = new Buf();
    long N, P, Q, X, Y;
    Map<Long, Long> map = new HashMap<>();
    public Sol(){
        N = buf.readLong();
        P = buf.readInt();
        Q = buf.readInt();
        X = buf.readInt();
        Y = buf.readInt();
        System.out.println(dfs(N));
    }
    public long dfs(long n){
        if (n<=0)return 1;
        if (map.containsKey(n))return map.get(n);

        map.put(n, dfs(n/P-X)+dfs(n/Q-Y));
        return map.get(n);
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