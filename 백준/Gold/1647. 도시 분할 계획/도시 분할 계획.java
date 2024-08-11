import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args){
        new sol();
    }


}
class sol{
    Buf buf = new Buf();
    int N, M;
    public sol() {
        N = buf.getInt();
        M = buf.getInt();
        House[] houses = new House[N];
        for (int i = 0; i<N; i++){
            houses[i] = new House();
        }
        for (int i = 0; i<M; i++){
            int s = buf.getInt()-1;
            int e = buf.getInt()-1;
            int w = buf.getInt();
            Road roadA = new Road(e, w);
            Road roadB = new Road(s, w);
            houses[s].addLink(roadA);
            houses[e].addLink(roadB);
        }

        int result = 0;
        int max = 0;
        PriorityQueue<Road> roadq = new PriorityQueue<>(Comparator.comparingInt(Road::getW));
        roadq.addAll(houses[0].getLink());
        boolean[] isVisited = new boolean[N];
        isVisited[0] = true;
        while(!roadq.isEmpty()){
            Road road = roadq.poll();
            if (isVisited[road.getE()])continue;
            isVisited[road.getE()] = true;
            roadq.addAll(houses[road.getE()].getLink());
            result+=road.getW();
            max=Math.max(road.getW(), max);
        }

        System.out.println(result-max);
    }
}
class Road {
    private int e;
    private int w;

    public Road( int e, int w) {
        this.e = e;
        this.w = w;
    }

    public int getE() {
        return e;
    }

    public int getW() {
        return w;
    }
}
class House{
    private final ArrayList<Road> link = new ArrayList<>();

    public void addLink(Road l){
        this.link.add(l);
    }

    public ArrayList<Road> getLink() {
        return link;
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