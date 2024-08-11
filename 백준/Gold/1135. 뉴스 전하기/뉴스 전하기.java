import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args){
        new Sol();
    }
}
class Sol{
    private final Buf buf = new Buf();
    private Node[] nodes;
    private int n;
    public Sol() {
        n = buf.readInt();
        nodes = new Node[n];
        for(int i = 0; i<n; i++){
            int input = buf.readInt();
            if (input==-1){
                nodes[i] = new Node();
            }else{
                if (nodes[input]==null)nodes[input]=new Node();
                nodes[i] = new Node(input);
                nodes[input].increaseChildNum();
            }
        }
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i<n; i++){
            if (nodes[i].getChildNum()==0){
                q.add(nodes[i]);
            }
        }
        while(!q.isEmpty()){
            Node poll = q.poll();
            PriorityQueue<Integer> pq = poll.getQ();
            int tmp = 1;
            int max = 0;
            while(!pq.isEmpty()){
                max = Math.max(pq.poll()+tmp,max);
                tmp++;
            }
            if (poll==nodes[0]){
                System.out.println(max);
                return;
            }
            Node parent = nodes[poll.getParent()];
            parent.addQ(max);
            parent.decreaseChildNum();
            if (parent.getChildNum()==0){
                q.add(parent);
            }
        }
    }
}
class Node{
    private int childNum;
    private PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2-o1);
    private List<Node> child = new ArrayList<>();
    private int parentIdx;

    public Node() {
        childNum = 0;
    }

    public Node(int parentIdx) {
        this.parentIdx = parentIdx;
    }

    public void increaseChildNum(){
        childNum++;
    }
    public void decreaseChildNum(){
        childNum--;
    }
    public void addQ(int max){
        q.add(max);
    }

    public int getChildNum() {
        return childNum;
    }

    public PriorityQueue<Integer> getQ() {
        return q;
    }

    public int getParent() {
        return parentIdx;
    }
}
class Buf{
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }

    public Integer readInt(){
        return Integer.parseInt(read());
    }
}