import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new sol();
    }
}
class sol{
    Buf buf = new Buf();
    public sol(){
        int N = buf.getInt();
        Non[] nons = new Non[N+1];
        boolean[] isVisited = new boolean[N+1];
        nons[N] = new Non();
        for (int  i =0; i<N; i ++){
            nons[N].addNon(new Route(buf.getInt(), i));
        }

        for (int i = 0; i<N; i++){
            nons[i] = new Non();
            for (int j = 0; j<N; j++){
                if (i==j){
                    buf.get();continue;
                }
                nons[i].addNon(new Route(buf.getInt(), j));
            }
        }
        int result = 0;
        int i = 0;
        PriorityQueue<Route> q = new PriorityQueue<>(Comparator.comparingInt(Route::getWeight));
        q.addAll(nons[N].linkedNon);
        isVisited[N] = true;
        while(i!=N){
            Route poll = q.poll();
            if (isVisited[poll.nonIdx])continue;
            result+= poll.getWeight();
            isVisited[poll.nonIdx] = true;
            q.addAll(nons[poll.nonIdx].linkedNon);
            i++;
        }
        System.out.println(result);
    }
}
class Non {
    List<Route> linkedNon = new ArrayList<>();
    public void addNon(Route route){
        linkedNon.add(route);
    }
}
class Route{
    int weight;
    int nonIdx;

    public Route(int weight, int nonIdx) {
        this.weight = weight;
        this.nonIdx = nonIdx;
    }

    public int getWeight() {
        return weight;
    }

    public int getNon() {
        return nonIdx;
    }
}
class Buf{
    BufferedReader br;
    StringTokenizer st;
    Buf() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public String get(){
        while(st==null||!st.hasMoreElements()){
            try{
                st= new StringTokenizer(br.readLine());
            }catch(IOException e){
                return null;
            }
        }
        return st.nextToken();
    }
    public Integer getInt(){
        return Integer.parseInt(get());
    }
}