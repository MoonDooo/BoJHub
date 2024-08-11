import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args){
        new Sol();
    }
}
class Sol{
    private final Buf buf = new Buf();
    private List<Integer> hex = new ArrayList<>();
    public Sol() {
        hex.add(1);
        int idx=0;
        for (int i = 1; hex.get(i-1)<=1000000; i++){
            hex.add(hex.get(i-1) + i*6 - (2*i-1));
        }

        int[] dp = new int[1000001];
        for (int i = 1; i<1000001; i++){
            int j = 0;
            int min = 7;
            while(hex.get(j)<=i){
                min = Math.min(dp[i-hex.get(j)]+1, min);
                j++;
            }
            dp[i] = min;
        }
        int N = buf.readInt();
        System.out.println(dp[N]);
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
}