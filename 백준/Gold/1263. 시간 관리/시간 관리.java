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
    private Buf buf = new Buf();
    public int N, result;
    public Sol() {
        N = buf.readInt();
        PriorityQueue<Work> pq = new PriorityQueue<>((o1, o2) ->  o2.si-o1.si);
        for (int i = 0; i < N; i++) {
            pq.add(new Work(buf.readInt(), buf.readInt()));
        }
        int rest = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            Work poll = pq.poll();
            rest = Math.min(rest, poll.si);
            rest -= poll.ti;
        }
        System.out.println(rest < 0 ? -1 : rest);
    }
}
class Work{
    int ti;
    int si;

    public Work(int ti, int si) {
        this.ti = ti;
        this.si = si;
    }
}
class Buf{
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;
    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }catch (IOException e){
                return null;
            }
        }
        return st.nextToken();
    }
    public Integer readInt(){
        return Integer.parseInt(read());
    }
}