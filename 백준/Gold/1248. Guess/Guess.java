import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Guess();
    }
}
class Guess{
    private int N;
    private char[][] input;
    private Buf buf = new Buf();
    private int[] result;

    public Guess(){
        initData();
    }

    private void initData() {
        N = buf.nextInt();
        String next = buf.next();
        input = new char[N][N];
        result = new int[N];
        int idx = 0;
        for(int i = 0; i<N; i++){
            for(int j = i; j<N; j++){
                input[i][j] = next.charAt(idx);
                idx++;
            }
        }
        calculating(0);
        for(int i = 0; i<N; i++){
            System.out.print(result[i] + " ");
        }
    }

    public boolean calculating(int idx){
        if (idx==N){
            return true;
        }
        int i = inputIIdx(idx);
        int j = inputJIdx(idx);

        for (; i<=j; i++){
            if (isCan(idx, i)){
                result[idx] = i;
                if (calculating(idx+1)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCan(int idx, int num) {
        for (int i = 0; i<idx; i++){
            int sum = 0;
            for (int j = i; j<idx;j ++){
                sum += result[j];
            }
            sum += num;
            if(input[i][idx]=='-'&&sum>=0){
                return false;
            }else if (input[i][idx]=='+'&&sum<=0){
                return false;
            }else if (input[i][idx]=='0'&&sum!=0){
                return false;
            }
        }
        return true;
    }

    private int inputJIdx(int idx) {
        if (input[idx][idx]=='-'){
            return -1;
        }else if (input[idx][idx]=='0'){
            return 0;
        }else{
            return 10;
        }
    }

    private int inputIIdx(int idx) {
        if(input[idx][idx]=='+'){
            return 1;
        }
        else if (input[idx][idx]=='0'){
            return 0;
        }
        else {
            return -10;
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