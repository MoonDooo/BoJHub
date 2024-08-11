import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new PlanetTunnel();
    }
}
class PlanetTunnel{
    Buf buf = new Buf();
    int N;
    Planet[] planets;
    PlanetTunnel(){
        PriorityQueue<Planet> xQ = new PriorityQueue<>(Comparator.comparingInt(Planet::getX));
        PriorityQueue<Planet> yQ = new PriorityQueue<>(Comparator.comparingInt(Planet::getY));
        PriorityQueue<Planet> zQ = new PriorityQueue<>(Comparator.comparingInt(Planet::getZ));

        N = buf.getInt();
        planets = new Planet[N];

        for (int i  =0; i<N; i++){
            planets[i] = new Planet(i, buf.getInt(), buf.getInt(), buf.getInt());
            xQ.add(planets[i]);
            yQ.add(planets[i]);
            zQ.add(planets[i]);
        }

        Planet nextXPlanet = xQ.poll();
        Planet nextYPlanet = yQ.poll();
        Planet nextZPlanet = zQ.poll();
        for (int i =0; i<N-1; i++){
            nextXPlanet = linkXPlanet(xQ, nextXPlanet);
            nextYPlanet = linkYPlanet(yQ, nextYPlanet);
            nextZPlanet = linkZPlanet(zQ, nextZPlanet);
        }

        boolean[] isVisited = new boolean[N];
        PriorityQueue<Tunnel> q = new PriorityQueue<>(Comparator.comparingInt(Tunnel::getWeight));
        long result = 0;
        q.addAll(planets[0].getTunnelList());
        isVisited[0] = true;
        int tmp = 0;
        while(tmp!=N-1){
            Tunnel poll = q.poll();
            int idx = poll.linkedPlanet.idx;
            if (!isVisited[idx]){
                result += poll.weight;
                isVisited[idx]=true;
                tmp++;
                q.addAll(planets[idx].tunnelList);
            }
        }
        System.out.println(result);
    }

    public Planet linkXPlanet(PriorityQueue<Planet> q, Planet currentP){
        Planet poll = q.poll();
        int abs = Math.abs(poll.getX() - currentP.getX());
        currentP.addTunnelList(new Tunnel(abs, poll));
        poll.addTunnelList(new Tunnel(abs, currentP));
        return poll;
    }
    public Planet linkYPlanet(PriorityQueue<Planet> q, Planet currentP){
        Planet poll = q.poll();
        int abs = Math.abs(poll.getY() - currentP.getY());
        currentP.addTunnelList(new Tunnel(abs, poll));
        poll.addTunnelList(new Tunnel(abs, currentP));
        return poll;
    }
    public Planet linkZPlanet(PriorityQueue<Planet> q, Planet currentP){
        Planet poll = q.poll();
        int abs = Math.abs(poll.getZ() - currentP.getZ());
        currentP.addTunnelList(new Tunnel(abs, poll));
        poll.addTunnelList(new Tunnel(abs, currentP));
        return poll;
    }
}
class Planet{
    int idx;
    int x;
    int y;
    int z;

    public void addTunnelList(Tunnel tunnel) {
        this.tunnelList.add(tunnel);
    }

    List<Tunnel> tunnelList = new ArrayList<>();

    public Planet(int idx, int x, int y, int z) {
        this.idx = idx;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public List<Tunnel> getTunnelList() {
        return tunnelList;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "idx=" + idx +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", tunnelList=" + tunnelList +
                '}';
    }
}
class Tunnel{
    int weight;
    Planet linkedPlanet;

    public Tunnel(int weight, Planet linkedPlanet) {
        this.weight = weight;
        this.linkedPlanet = linkedPlanet;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Tunnel{" +
                "weight=" + weight +
                ", linkedPlanet=" + linkedPlanet +
                '}';
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