import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    private final List<Long> input = new ArrayList<>();
    public Sol(){
        N = buf.readLong();
        M = buf.readLong();


        long max = 0;
        for (int i = 0; i<M; i++){
            input.add(buf.readLong());
            max = Math.max(input.get(i), max);
        }
        if (N<=M){
            System.out.println(N);
            return;
        }

        long right = max *N;
        long left = 0;
        while(left<right){
            long mid = (right+left)/2;
            if (inCan(mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }

        long tmp = M;
        for (int i = 0; i<M; i++){
            tmp += left/input.get(i);
        }
        tmp -= N;
        long result = 0;
        for (int i = (int) (M-1); 0<=i; i--){
            if (left%input.get(i)==0){
                if (tmp == 0){
                    result = i+1;
                    break;
                }
                tmp --;
            }
        }

        System.out.println(result);
    }
    public boolean inCan(long mid){
        long tmp = M;
        for (int i = 0; i<M; i++){
            tmp += mid/input.get(i);
        }
        return N <= tmp;
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
