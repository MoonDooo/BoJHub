import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new SayMiddle();
    }
}
class SayMiddle{
    private final PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
    private final PriorityQueue<Integer> minQueue = new PriorityQueue<>();
    private final Buf buf= new Buf();
    public SayMiddle() throws IOException {
        int N = buf.nextInt();
        StringBuilder st= new StringBuilder();
        for (int i  =0; i<N; i++){
            if (i%2==0){
               minQueue.add(buf.nextInt());
            }else{
                maxQueue.add(buf.nextInt());
            }
            int a = 0;
            int b = 0;
            if (maxQueue.isEmpty()){
                st.append(minQueue.peek()).append("\n");
                continue;
            } else{
                a = minQueue.poll();
                b = maxQueue.poll();
            }

            if (maxQueue.size()<minQueue.size()){
                if (a<b){
                    minQueue.add(b);
                    maxQueue.add(a);
                    st.append(b).append("\n");
                }else{
                    minQueue.add(a);
                    maxQueue.add(b);
                    st.append(a).append("\n");
                }
            }else{
                if (a<b){
                    minQueue.add(b);
                    maxQueue.add(a);
                    st.append(a).append("\n");
                }else{
                    minQueue.add(a);
                    maxQueue.add(b);
                    st.append(b).append("\n");
                }
            }
        }
        System.out.println(st);
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