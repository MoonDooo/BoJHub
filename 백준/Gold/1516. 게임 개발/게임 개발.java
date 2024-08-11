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
    private int[] result;
    private Queue<Integer> nodeQueue=  new LinkedList<>();
    public GameDevelopment(){
        initData();
        for (int i = 0; i<N; i++){
            if(nodes[i].getPre()==0){
                nodeQueue.add(i);
                result[i] = nodes[i].getDelay();
            }
        }
        while(!nodeQueue.isEmpty()){
            int nodeIdx = nodeQueue.poll();
            Node node = nodes[nodeIdx];
            for (int i =0; i<node.getLinkedNodeIdx().size(); i++){
                int linkedNodeIdx = node.getLinkedNodeIdx().get(i);
                Node linkedNode = nodes[linkedNodeIdx];
                if (result[linkedNodeIdx]<result[nodeIdx]+linkedNode.getDelay()){
                    result[linkedNodeIdx] = result[nodeIdx]+linkedNode.getDelay();
                }
                linkedNode.reducePre();
                if (linkedNode.getPre()==0){
                    nodeQueue.add(linkedNodeIdx);
                }
            }
        }

        Arrays.stream(result).forEach(System.out::println);
    }

    private void initData() {
        N = buf.nextInt();
        nodes = new Node[N];
        result = new int[N];
        for (int  i =0; i<N; i++){
            nodes[i] = new Node();
        }
        for (int i  =0; i<N; i++){
            nodes[i].setDelay(buf.nextInt());
            int input;
            while((input=buf.nextInt())!=-1){
                nodes[input-1].addLinkedNodeIdx(i);
                nodes[i].increasePre();
            }
        }
    }

}

class Node{
    private final List<Integer> linkedNodeIdx = new ArrayList<>();
    private int pre;
    private int delay;
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Node() {
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
    public List<Integer> getLinkedNodeIdx() {
        return linkedNodeIdx;
    }

    public int getPre() {
        return pre;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public String toString() {
        return "Node{" +
                "linkedNodeIdx=" + linkedNodeIdx +
                ", pre=" + pre +
                ", delay=" + delay +
                '}';
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