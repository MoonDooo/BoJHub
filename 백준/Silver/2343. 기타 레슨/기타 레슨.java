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
    int N,M;
    private final Buf buf = new Buf();
    int[] input;
    public Sol(){
        N = buf.readInt();
        M = buf.readInt();
        input = new int[N];
        int max = 0;
        for (int i = 0; i<N; i++){
            input[i] = buf.readInt();
            max += input[i];
        }
        int right = max;
        int left = 0;

        while(left+1<=right){
            int mid = (right+left)/2;
            if (isCan((mid))){
                right = mid;
            }else{
                left =mid+1;
            }
        }
        System.out.println(right);
    }
    public boolean isCan(int mid){
        int[] bluRay = new int[M];
        int idx = 0;
        for (int i = 0; i<M; i++){
            while(idx<N&&(bluRay[i]+input[idx])<=mid){
                bluRay[i]+=input[idx];
                idx++;
            }
        }
        return idx==N;
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
}