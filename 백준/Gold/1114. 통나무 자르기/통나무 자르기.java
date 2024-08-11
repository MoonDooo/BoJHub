import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    int L, C, K;
    private final Buf buf = new Buf();
    List<Integer> input = new ArrayList<>();
    public Sol(){
        L = buf.readInt();
        K = buf.readInt();
        C = buf.readInt();

        Set<Integer> inputSet = new HashSet<>();
        for (int i = 0; i<K; i++){
            inputSet.add(buf.readInt());
        }

        input.addAll(inputSet);
        input.add(L);
        input.sort(Comparator.comparingInt(o -> o));

        int right = L;
        int left = 0;
        while(left<right){
            int mid = (right+left)/2;
            if (isCan(0, 0, mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        int result = 0;
        for (int i = 0; i<input.size(); i++){
            if (isCan(input.get(i), 1, left)){
                result = input.get(i);
                break;
            }
        }
        System.out.println(right + " " + result);
    }
    public boolean isCan(int tmp, int idx, int mid){
        int pre = 0;
        boolean isCan = false;
        for(int i  =0; i<input.size();){
            if (input.get(i)<=tmp+mid){
                isCan = true;
                pre = input.get(i);
                i++;
            }
            else{
                if (!isCan)return false;
                isCan=false;
                tmp = pre;
                idx++;
            }
        }
        return idx<=C;
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