import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Party();
    }
}
class Party{
    private PriorityQueue<Node> queue;
    private Node[] nodes;
    private final Buf buf = new Buf();
    private int[] result;
    private int N, M, X;

    public Party(){
        initData();
        calculating(true);
        for(int i = 0; i<N; i++){
            result[i] = nodes[i].getWeight();
            nodes[i].setWeight(Integer.MAX_VALUE);
            nodes[i].isVisited = false;
        }
        calculating(false);
        int max = 0;
        for(int i = 0; i<N; i++){
            if (max<nodes[i].getWeight()+result[i]){
                max = nodes[i].getWeight()+result[i];
            }
        }
        System.out.println(max);
    }

    private void calculating(boolean b) {
        queue.add(nodes[X-1]);
        nodes[X-1].setWeight(0);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if (node.isVisited)continue;
            else node.isVisited = true;
            for(int i = 0; i < getLinkedNodeSize(node, b); i++){
                int linkedNodeIdx = getLinkedNodeIdx(node, i, b);
                int w = getW(node, i, b);
                if (w<nodes[linkedNodeIdx].getWeight()){
                    nodes[linkedNodeIdx].setWeight(w);
                }
                queue.add(nodes[linkedNodeIdx]);
            }
        }
    }

    private static int getLinkedNodeIdx(Node node, int i, boolean b) {
        if (b){
            return node.getFront().get(i).getLinkedNodeIdx();
        }else{
            return node.getBack().get(i).getLinkedNodeIdx();
        }
    }

    private static int getLinkedNodeSize(Node node, boolean b) {
        if (b){
            return node.getFront().size();
        }else{
            return node.getBack().size();
        }

    }

    private static int getW(Node node, int i, boolean b) {
        if (b){
            return node.getWeight() + node.getFront().get(i).getLinkedWeight();
        }else{
            return node.getWeight() + node.getBack().get(i).getLinkedWeight();
        }
    }

    private void initData() {
        N = buf.nextInt();
        M = buf.nextInt();
        X = buf.nextInt();

        queue = new PriorityQueue<>();

        nodes = new Node[N];
        result = new int[N];

        for(int i = 0; i<N; i++){
            nodes[i] = new Node();
        }
        for (int i = 0; i<M; i++){
            int startNodeIdx = buf.nextInt()-1;
            int endNodeIdx = buf.nextInt()-1;
            int linkedWeight = buf.nextInt();
            nodes[startNodeIdx].addFront(new Line(endNodeIdx,  linkedWeight));
            nodes[endNodeIdx].addBack(new Line(startNodeIdx, linkedWeight));
        }
    }
}
class Node implements Comparable<Node> {
    private final List<Line> front;
    private final List<Line> back;
    boolean isVisited;
    private int weight;

    public Node(){
        front = new ArrayList<>();
        back = new ArrayList<>();
        weight = Integer.MAX_VALUE;
    }

    public void addFront(Line line){
        this.front.add(line);
    }

    public void addBack(Line line){
        this.back.add(line);
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);
    }

    public List<Line> getFront() {
        return front;
    }

    public List<Line> getBack() {
        return back;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
class Line{
    private final int linkedNodeIdx;
    private final int linkedWeight;

    public Line(int linkedNodeIdx, int weight){
        this.linkedNodeIdx = linkedNodeIdx;
        this.linkedWeight = weight;
    }

    public int getLinkedNodeIdx() {
        return linkedNodeIdx;
    }

    public int getLinkedWeight() {
        return linkedWeight;
    }
}
class Buf{
    BufferedReader br;
    StringTokenizer st;
    Buf(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    String next(){
        while(st==null||!st.hasMoreElements()){
            try{
                st= new StringTokenizer(br.readLine());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
}
