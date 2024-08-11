
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new CardOrganization();
    }
}
class CardOrganization{
    private final Buf buf = new Buf();
    private int N;
    private int M;
    private int eachMin;
    private int result;
    private int packageMin;
    public CardOrganization() {
        initData();
        result = 0;
        while(0<N){
            if (6<=N){
                result+=packageMin;
                N-=6;
            }else{
                if (packageMin<N*eachMin){
                    result+=packageMin;
                }else{
                    result+=N*eachMin;
                }
                break;
            }
        }
        System.out.println(result);
    }
    public void initData(){
        N = buf.nextInt();
        M = buf.nextInt();
        packageMin = Integer.MAX_VALUE;
        eachMin = Integer.MAX_VALUE;
        for (int i = 0; i<M; i++){
            packageMin = Math.min(packageMin, buf.nextInt());
            eachMin = Math.min(eachMin, buf.nextInt());
            packageMin = Math.min(packageMin, eachMin*6);
        }
    }
}

class Buf{
    BufferedReader br;
    StringTokenizer st;
    Buf(){
        br= new BufferedReader(new InputStreamReader(System.in));
    }
    String next(){
        while(st==null||!st.hasMoreElements()){
            try{
                st= new StringTokenizer(br.readLine());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }
    int nextInt(){
        return Integer.parseInt(next());
    }
    long newLong(){
        return Long.parseLong(next());
    }
}
