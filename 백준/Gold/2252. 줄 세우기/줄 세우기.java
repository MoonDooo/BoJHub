import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new GameDevelopment();
    }
}
class GameDevelopment{
    private final Buf buf = new Buf();
    private Node[] nodes;
    private int N;
    private int M;
    private Queue<Integer> nodeQueue=  new LinkedList<>();
    public GameDevelopment(){
        initData();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<N; i++){
            if(nodes[i].getPre()==0){
                nodeQueue.add(i);
            }
        }
        while(!nodeQueue.isEmpty()){
            int nodeIdx = nodeQueue.poll();
            Node node =nodes[nodeIdx];
            sb.append(node.getNum()).append(" ");
            node.getLinkedNodeIdx().forEach(idx->{
                Node linkedNode = nodes[idx];
                linkedNode.reducePre();
                if (linkedNode.getPre()==0){
                    nodeQueue.add(idx);
                }
            });
        }

        System.out.println(sb);
    }

    private void initData() {
        N = buf.nextInt();
        M = buf.nextInt();
        nodes = new Node[N];
        for (int  i =0; i<N; i++){
            nodes[i] = new Node(i+1);
        }
        for (int i  =0; i<M; i++){
            int aIdx = buf.nextInt();
            int bIdx = buf.nextInt();
            nodes[aIdx-1].addLinkedNodeIdx(bIdx-1);
            nodes[bIdx-1].increasePre();
        }
    }

}

class Node{
    private final int num;
    private final Set<Integer> linkedNodeIdx = new HashSet<>();
    private int pre;

    public Node(int num) {
        this.num = num;
        pre = 0;
    }
    public void addLinkedNodeIdx(int idx){
        this.linkedNodeIdx.add(idx);
    }

    public void reducePre(){
        this.pre--;
    }

    public void increasePre(){
        this.pre++;
    }
    public Set<Integer> getLinkedNodeIdx() {
        return linkedNodeIdx;
    }

    public int getPre() {
        return pre;
    }


    @Override
    public String toString() {
        return "Node{" +
                "linkedNodeIdx=" + linkedNodeIdx +
                ", pre=" + pre +
                '}';
    }

    public int getNum() {
        return num;
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