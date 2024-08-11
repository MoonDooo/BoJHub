import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    int N;
    long M;
    private final Buf buf = new Buf();
    long[] input;
    public Sol(){
        N = buf.readInt();
        M = buf.readInt();
        input = new long[N];
        long min = Long.MAX_VALUE;
        for (int i = 0; i<N; i++){
            input[i] = buf.readLong();
            min = Math.min(input[i], min);
        }
        long right = min*M;
        long left = 0;

        while(left+1<=right){
            long mid = (right+left)/2;
            if (isCan((mid))){
                right = mid;
            }else{
                left =mid+1;
            }
        }
        System.out.println(right);
    }
    public boolean isCan(long mid){
        long idx = 0;
        for (int i = 0; i<N; i++){
            idx+=(mid/input[i]);
        }
        return M<=idx;
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