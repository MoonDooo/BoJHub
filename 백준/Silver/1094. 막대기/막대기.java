import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new makdaegi();
    }
}
class makdaegi{
    private final Buf buf = new Buf();
    public makdaegi(){
        int N = buf.nextInt();
        int result = 0;
        for (int i = 0; i<8; i++){
            if (0<(1<<i&N)){
                result++;
            }
        }
        System.out.println(result);
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