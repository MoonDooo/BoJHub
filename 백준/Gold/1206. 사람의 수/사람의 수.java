import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    Buf buf = new Buf();
    int N;
    int[] input;

    public Sol(){
        N = buf.readInt();
        input = new int[N];
        for (int i = 0; i<N; i++){
            String input = buf.read();
            int idx = input.indexOf(".");
            this.input[i] = Integer.parseInt(input.substring(idx+1));
        }
        int n=0;
        while(true){
            n++;
            int result = 0;

            for (int i = 0; i < N; i++){
                if ((input[i] * n)%1000==0||(1000-n)<(input[i]*n%1000))result++;
                else break;
            }
            if (result == N)break;;
        }
        System.out.println(n);
    }
}
class Buf{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;

    public String read(){
        while(st == null || !st.hasMoreTokens()){
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                return null;
            }
        }
        return st.nextToken();
    }

    public Integer readInt(){
        return Integer.parseInt(read());
    }

    public float readFloat(){
        return Float.parseFloat(read());
    }
}