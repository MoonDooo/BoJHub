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
        int N = buf.getInt();
        int M = buf.getInt();


        int[] root = new int[N];
        PriorityQueue<Route> q = new PriorityQueue<>((o1, o2) -> {
            if (o1.s < o2.s){
                return -1;
            }else if (o1.s > o2.s){
                return 1;
            }else{
                if (o1.e < o2.e){
                    return -1;
                }else if (o1.e > o2.e) {
                    return 1;

                }else{
                    return 0;
                }
            }
        });
        for (int i  =0 ;i<N; i++){
            root[i] = i;
        }
        for (int i = 0;i <N; i++){
            String s = buf.get();
            for (int j =0; j<N; j++){
                if (i<j&&s.charAt(j)=='Y'){
                    q.add(new Route(i, j));
                }
            }
        }
        if (q.size()<M){
            System.out.println(-1);
            return;
        }
        int[] height = new int[N];
        Arrays.fill(height,1);
        int[] result = new int[N];
        List<Route> isCircleRoute = new ArrayList<>();
        int tmp = 0;
        while (tmp!=N-1){
            if (q.isEmpty()){
                System.out.println(-1);
                return;
            }
            Route poll = q.poll();
            if (union(root, height, poll.s, poll.e)){
                result[poll.s]++;
                result[poll.e]++;
                tmp++;
            }else{
                isCircleRoute.add(poll);
            }
        }
        if (tmp!=N-1){
            System.out.println(-1);
            return;
        }
        q.addAll(isCircleRoute);

        for (int i =0 ; i<M-N+1; i++){
            if (q.isEmpty()){
                System.out.println(-1);
                return;
            }
            Route poll = q.poll();
            result[poll.s]++;
            result[poll.e]++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i =0 ;i<N; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
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

    public Route(int s, int e) {
        this.s = s;
        this.e = e;
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