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
class Sol {
    Buf buf = new Buf();
    int N;
    int[] input;
    int[][] R;
    boolean[] isDie;
    int M;
    int result;
    public Sol(){
        N = buf.readInt();
        isDie = new boolean[N];
        input = new int[N];
        R = new int[N][N];
        for (int i = 0; i < N; i++){
            input[i] = buf.readInt();
        }
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                R[i][j] = buf.readInt();
            }
        }
        result = 0;
        M = buf.readInt();
        if (N%2 == 0) night(0);
        else day(0);
        System.out.println(result);
    }
    public void night(int d){

        for (int i = 0; i<N; i++){
            if (i!=M&&!isDie[i]){
                //i번째 참여자 살해
                add(input, i);
                result = Math.max(d+1, result);
                isDie[i] = true;
                day(d+1);
                minus(input,i);
                isDie[i] = false;
            }
        }
    }
    public void day(int d){
        int max = 0;
        int maxIdx = 0;
        for (int i = 0; i<N; i++){
            //사형 결정
            if (isDie[i])continue;
            if (max < input[i]){
                max = input[i];
                maxIdx = i;
            }
        }
        isDie[maxIdx] = true;
        if (!isDie[M]) night(d);
        isDie[maxIdx] = false;
    }
    public void add(int[] tmp, int idx){
        for (int i = 0; i < N; i++){
            tmp[i] += R[idx][i];
        }
    }
    public void minus(int[] tmp, int idx){
        for (int i =0; i <N; i++){
            tmp[i] -= R[idx][i];
        }
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