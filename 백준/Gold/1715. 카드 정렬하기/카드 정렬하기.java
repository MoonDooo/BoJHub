import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    private final Buf buf = new Buf();
    private final PriorityQueue<Integer> queue = new PriorityQueue<>();

    public Sol(){
        int result = 0;
        int n = buf.nextInt();
        while(n-->0){
            int input = buf.nextInt();
            queue.add(input);
        }
        while(1<queue.size()){
            int a = queue.poll();
            int b = queue.poll();
            queue.add(a+b);
            result+=(a+b);
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