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
    List<Route> result = new ArrayList<>();
    public sol(){
        int V = buf.getInt();
        int E = buf.getInt();


        int[] root = new int[V];
        PriorityQueue<Route> q = new PriorityQueue<>(Comparator.comparingInt(Route::getWeight));
        for (int i  =0 ;i<V; i++){
            root[i] = i;
        }
        for (int i = 0;i <E; i++){
            int s = buf.getInt()-1;
            int e = buf.getInt()-1;
            int w = buf.getInt();
            Route input = new Route(w, s, e);
            q.add(input);
        }

        int i = 0;
        int[] height = new int[V];
        Arrays.fill(height,1);
        int result = 0;
        while(i!=V-1){
            Route poll = q.poll();
            if (union(root, height, poll.s, poll.e)){
                i++;
                result += poll.weight;
            }
        }
        System.out.println(result);
    }

    public int find(int[] root, int idx){
        if (root[idx] != idx) {
            root[idx] = find(root, root[idx]);
        }
        return root[idx];
    }

    public boolean union(int[] root, int[] height, int a, int b){
        int rootA = find(root, a);
        int rootB = find(root, b);
        if (rootA == rootB)return false;
        if (height[rootA]<=height[rootB]){
            root[rootA] = root[rootB];
            if (height[rootA]==height[rootB])height[rootB]++;

        }else{
            root[rootB] = root[rootA];
        }
        return true;
    }
}

class Route{
    int weight;
    int s;
    int e;

    public Route(int weight, int s, int e) {
        this.weight = weight;
        this.s = s;
        this.e = e;
    }


    public int getWeight() {
        return weight;
    }

    public int getS() {
        return s;
    }

    public int getE() {
        return e;
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