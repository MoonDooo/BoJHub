import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try{
            new Dijkstra();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
class Node{
    int nodeNum;
    ArrayList<Node> linkedNode;
    ArrayList<Integer> distance;
    public Node(int num) {
        nodeNum = num;
        this.linkedNode = new ArrayList<>();
        this.distance = new ArrayList<>();
    }

    public void InsertLinkedNode(Node node, int d){
        linkedNode.add(node);
        distance.add(d);
    }

}
class QueueNode implements Comparable<QueueNode> {
    public QueueNode(int distance, int nodeNum) {
        this.distance = distance;
        this.nodeNum = nodeNum;
    }

    int distance;
    int nodeNum;

    @Override
    public int compareTo(QueueNode o) {
        if(this.distance>o.distance){
            return 1;
        }
        else if(this.distance<o.distance){
            return -1;
        }
        return 0;
    }
}
class Dijkstra{
    private ArrayList<Node> nodes;
    private ArrayList<Boolean> isVisited;
    private PriorityQueue<QueueNode> queue;
    private int startNode;
    private int endNode;
    public Dijkstra() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cityNum = Integer.parseInt(br.readLine());
        int busNum = Integer.parseInt(br.readLine());
        CreateCity(cityNum);
        for(int i = 0; i<busNum; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes.get(s-1).InsertLinkedNode(nodes.get(e-1), cost);
        }
        st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken())-1;
        endNode = Integer.parseInt(st.nextToken())-1;

        queue = new PriorityQueue<>();

        Solve();

    }
    public void CreateCity(int cityNum){
        nodes = new ArrayList<>();
        isVisited = new ArrayList<>();
        for(int i = 0; i<cityNum; i++){
            Node newNode = new Node(i);
            nodes.add(newNode);
            isVisited.add(false);
        }
    }

    public void Solve(){
        queue = new PriorityQueue<>();
        for(int i = 0; i<nodes.get(startNode).distance.size(); i++){
            QueueNode qNode = new QueueNode(nodes.get(startNode).distance.get(i), nodes.get(startNode).linkedNode.get(i).nodeNum);
            queue.add(qNode);
        }
        int targetNode = -1;
        int max = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            QueueNode qNode = queue.poll();
            if(qNode.nodeNum==endNode&&max>qNode.distance){
                max = qNode.distance;
            }

            if(isVisited.get(qNode.nodeNum)){
                continue;
            }else{
                isVisited.set(qNode.nodeNum, true);
            }

            for(int i = 0; i<nodes.get(qNode.nodeNum).distance.size(); i++){
                QueueNode inNode = new QueueNode(nodes.get(qNode.nodeNum).distance.get(i)+qNode.distance, nodes.get(qNode.nodeNum).linkedNode.get(i).nodeNum);
                queue.add(inNode);
            }
        }
        System.out.println(max);
    }
}

