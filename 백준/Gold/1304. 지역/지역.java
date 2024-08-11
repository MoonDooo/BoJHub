import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class  Main {
    public static void main(String[] args) {
        new Region();
    }
}
class Region{
    private final Buf buf = new Buf();
    private int N, M;
    private int[] isLinked;
    public Region() {
        initData();
        for (int i = 1; 2*i<=N; i++){
            if (N%i!=0)continue;
            boolean b= false;
            for (int j = 0; i*j<N; j++){
                for (int k = j; k<(j+1)*i; k++){
                    if ((j+1)*i<=isLinked[k]){
                        b = true;
                        break;
                    }
                }
                if (b)break;
            }
            if (!b){
                System.out.println(N/i);
                return;
            }
        }
        System.out.println(1);
    }

    private void initData() {
        N = buf.nextInt();
        M = buf.nextInt();
        isLinked = new int[N];
        for(int i = 0; i<M; i++){
            int a = buf.nextInt()-1;
            int b = buf.nextInt()-1;
            if (b<a){
                for (int j = b; j<a;j++){
                    if (isLinked[j]<a) isLinked[j] = a;
                }
            }
        }
    }
}
class Buf{
    BufferedReader br;
    StringTokenizer st;
    Buf(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    String next(){
        while(st==null||!st.hasMoreElements()){
            try{
                st= new StringTokenizer(br.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
}
