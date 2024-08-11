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
    public sol(){
        int N = buf.getInt();
        int P = buf.getInt();
        int[] root = new int[N];
        int[] cost = new int[N];
        PriorityQueue<Route> q = new PriorityQueue<>(Comparator.comparingInt(Route::getWeight));
        for (int i  =0 ;i<N; i++){
            root[i] = i;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0;i <N; i++){
            cost[i] = buf.getInt();
            min = Math.min(min, cost[i]);
        }
        for (int i = 0;i<P;i++){
            int s = buf.getInt()-1;
            int e = buf.getInt()-1;
            int weight = 2*buf.getInt()+cost[s]+cost[e];
            q.add(new Route(s, e, weight));
        }
        int[] height = new int[N];
        Arrays.fill(height,1);
        int tmp = 0;
        int result = 0;
        while (tmp!=N-1){
            Route poll = q.poll();
            if (union(root, height, poll.s, poll.e)){
                result += poll.getWeight();
                tmp++;
            }
        }

        System.out.println(result+min);
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
    int s;
    int e;
    int weight;
    public Route(int s, int e, int weight) {
        this.s = s;
        this.e = e;
        this.weight = weight;
    }


    public int getS() {
        return s;
    }

    public int getE() {
        return e;
    }

    public int getWeight() {
        return weight;
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