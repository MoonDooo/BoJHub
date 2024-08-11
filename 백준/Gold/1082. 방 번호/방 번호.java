import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new RoomNumber();
    }
}
class RoomNumber{
    private final Buf buf = new Buf();
    private String[] input;
    private String[] dp;
    private int N, M;

    public RoomNumber(){
        initData();
        for(int i = 0; i<M+1; i++){
            for(int j = 0; j<N; j++){
                int money = i;
                if(0<=(money -=getInputInt(j))){
                    if(dp[i].equals("x")){
                        dp[i] = String.valueOf(j);
                    }
                    if(0<money&&!dp[money].equals("x")){
                        String f = dp[money] + j;
                        String b = j + dp[money];
                        if(compare(f, b)){
                            if(compare(dp[i], b)){
                                dp[i] = b;
                            }
                        }else{
                            if(compare(dp[i], f)){
                                dp[i] = f;
                            }
                        }
                    }else{
                        if(compare(dp[i], String.valueOf(j))){
                            dp[i] = String.valueOf(j);
                        }
                    }
                }
            }
        }
        System.out.println(dp[M]);
    }

    public boolean compare(String s1, String s2){
        if(s1.charAt(0) == '0'){
            s1 = s1.replaceFirst("0", "");
        }
        if (s2.charAt(0) == '0'){
            s2 = s2.replaceFirst("0", "");
        }
        s2 = s2.replaceFirst("0","");
        if(s1.length()<s2.length()){
            return true;
        }else if (s1.length()>s2.length()){
            return false;
        }else{
            for(int i = 0; i<s1.length(); i++){
                if((int)s1.charAt(i)<(int)s2.charAt(i)){
                    return true;
                }else if((int)s1.charAt(i)>(int)s2.charAt(i)){
                    return false;
                }
            }
        }
        return false;
    }
    private int getInputInt(int j) {
        return Integer.parseInt(input[j]);
    }

    private void initData() {
        N = buf.nextInt();
        input = new String[N];
        for(int i = 0; i<N; i++ ){
            input[i] = buf.next();
        }
        M = buf.nextInt();
        dp = new String[M+1];
        for(int i =0; i<M+1; i++){
            dp[i] = "x";
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
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }
    int nextInt(){
        return Integer.parseInt(next());
    }

}