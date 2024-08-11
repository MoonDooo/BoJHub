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
    private final Buf buf = new Buf();
    private int testcase;

    public Sol(){
        testcase = buf.nextInt();
        for (int i = 0; i<testcase; i++){
            int a = buf.nextInt();
            int b = buf.nextInt();
            int tmp = a;
            for (int j = 1; j<b; j++){
                tmp *= a;
                tmp %= 10;
            }
            if (tmp%10 == 0){
                System.out.println(10);
            }else{
                System.out.println(tmp%10);
            }
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