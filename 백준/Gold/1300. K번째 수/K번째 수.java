import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    long N, M;
    private final Buf buf = new Buf();
    public Sol(){
        N = buf.readLong();
        M = buf.readLong();

        long right = (long) N *N;
        long left = 0;
        while(left<right){
            long mid = (right+left)/2;
            if (inCan(mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }

        System.out.println(left);

    }
    public boolean inCan(long mid){
        long tmp = 0;
        for (int i = 1; i<=N; i++){
            tmp += Math.min(mid/i, N);
        }
        return M <= tmp;
    }

}
class Buf{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }catch(IOException e){
                return null;
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