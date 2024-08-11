import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new GoodBrothers();
    }
}
class GoodBrothers{
    private final Buf buf = new Buf();
    public GoodBrothers() {
        long a = buf.nextLong();
        long b = buf.nextLong();
        boolean isCan = false;
        long tmp = 1;
        int length = 0;
        for (int i = 1; i<=60; i++){
            if (((1L <<i)-1)%b==0){
                tmp = ((1L<<i)-1)/b;
                length = i;
                isCan = true;
                break;
            }
        }
        if (!isCan){
            System.out.println(-1);
            return;
        }
        a *= tmp;
        for (int i = length-1; 0<=i; i--){
            if ((a>>i)==1){
                System.out.print("*");
                a-=(1L<<i);
            }else{
                System.out.print("-");
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
    long nextLong(){return Long.parseLong(next());}
}