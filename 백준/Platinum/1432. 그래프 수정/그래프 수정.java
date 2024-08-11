import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new EditGraph();
    }
}
class EditGraph{
    private final Buf buf = new Buf();
    private int N;
    private Node[] nodes;
    private int[] result;
    private PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->Integer.compare(b,a));
    public EditGraph(){
        initData();
        for (int  i = 0; i<N; i++){
            if (nodes[i].getPre()==0){
                queue.add(i);
            }
        }
        if (queue.isEmpty()){
            System.out.println(-1);
            System.exit(0);
        }
        int count = 0;
        while(!queue.isEmpty()){
            int nodeIdx = queue.poll();
            result[nodes[nodeIdx].getNum()] = N-count;
            count++;
            for (int i = 0; i<nodes[nodeIdx].getLinkedNodeIdxList().size(); i++){
                int linkedNodeIdx = nodes[nodeIdx].getLinkedNodeIdxList().get(i);
                nodes[linkedNodeIdx].reducePre();
                if (nodes[linkedNodeIdx].getPre()==0){
                    queue.add(linkedNodeIdx);
                }
            }
        }

        Arrays.stream(result).forEach(r->{
            if (r==0){
                System.out.println(-1);
                System.exit(0);
            }
        });
        Arrays.stream(result).forEach(r-> System.out.print(r+" "));
    }
    public void initData(){
        N = buf.nextInt();
        nodes = new Node[N];
        for (int i = 0; i<N; i++){
            nodes[i] = new Node(i);
        }
        result = new int[N];
        for (int i  =0; i<N; i++){
            String input = buf.next();
            for (int j = 0; j<N; j++){
                if (input.charAt(j)=='1'){
                    nodes[j].addLinkedNodeIdx(i);
                    nodes[i].increasePre();
                }
            }
        }
    }
}
class Node{
    private int num;
    private List<Integer> linkedNodeIdxList = new ArrayList<>();

    public int getPre() {
        return pre;
    }
    public void increasePre(){
        this.pre++;
    }
    public void reducePre(){
        this.pre--;
    }

    private int pre;

    public Node(int num) {
        this.num = num;
        this.pre = 0;
    }

    public int getNum() {
        return num;
    }

    public List<Integer> getLinkedNodeIdxList() {
        return linkedNodeIdxList;
    }
    public void addLinkedNodeIdx(int idx){
        linkedNodeIdxList.add(idx);
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