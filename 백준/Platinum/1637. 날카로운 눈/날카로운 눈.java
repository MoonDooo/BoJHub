import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Collator;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    int N;
    private final Buf buf = new Buf();
    List<ABC> input = new ArrayList<>();
    public Sol(){
        N = buf.readInt();
        for (int i = 0; i<N; i++){
            input.add(new ABC(buf.readInt(), buf.readInt(), buf.readInt()));
        }
        if (!isOddNum(Integer.MAX_VALUE)){
            System.out.println("NOTHING");
            return;
        }

        long right = Integer.MAX_VALUE;
        long left = 0;
        while(left<right){
            long mid = (right+left)/2;
            if (isOddNum(mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        System.out.println(left + " "+ num(left));
    }
    public boolean isOddNum(long mid){
        long tmp = 0;
        for (int i = 0;i<N; i++){
            ABC abc = input.get(i);
            if (mid<abc.a)continue;
            long K = Math.min(mid, abc.c);
            tmp+=(K-abc.a)/abc.b+1;
        }
        return tmp%2==1;
    }

    public int num(long a){
        int num = 0;
        for (int i =0;i<N;i++){
            ABC abc = input.get(i);
            if (abc.c<a||a<abc.a)continue;
            if ((a-abc.a)%abc.b==0)num++;
        }
        return num;
    }
}
class ABC{
    int a, c, b;

    public ABC(int a, int c, int b) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }
}
class Buf{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }catch(IOException e){
                return null;
            }
        }
        return st.nextToken();
    }
    public Integer readInt(){
        return Integer.parseInt(read());
    }

    public Long readLong(){
        return Long.parseLong(read());
    }
}