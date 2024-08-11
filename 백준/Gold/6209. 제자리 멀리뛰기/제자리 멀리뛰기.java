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
    int L, N, M;
    private final Buf buf = new Buf();
    private final List<Integer> input = new ArrayList<>();
    public Sol(){
        L = buf.readInt();
        N = buf.readInt();
        M = buf.readInt();
        input.add(0);
        input.add(L);
        for (int i = 0; i < N; i++){
            input.add(buf.readInt());
        }
        input.sort(Comparator.comparingInt(o -> o));
        int right = L;
        int left = 0;
        int result = 0;
        while(left<=right){
            int mid = (right+left)/2;
            if (inCan(mid)){
                right = mid-1;
            }else{
                result = mid;
                left = mid+1;
            }
        }

        System.out.println(result);

    }
    public boolean inCan(int mid){
        int tmp = 0;
        int idx = 0;
        for (int i = 1; i<input.size(); i++){
            if ((input.get(i)-input.get(idx))<mid){
                tmp++;
            }else{
                idx=i;
            }
        }
        return M<tmp;
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
