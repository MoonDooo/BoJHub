import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new lectureRoom2();
    }
}
class lectureRoom2{
    private final PriorityQueue<Lecture> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Lecture::getStartTime));
    private final PriorityQueue<Allocation> aq = new PriorityQueue<>(Comparator.comparingInt(a -> a.getLecture().getEndTime()));
    private final Buf buf = new Buf();
    private final int N;
    private final int[] result;
    public lectureRoom2(){
        N = buf.nextInt();
        result = new int[N];
        for (int i =0; i<N; i++){
            Lecture lecture = new Lecture(buf.nextInt()-1, buf.nextInt(), buf.nextInt());
            priorityQueue.offer(lecture);
        }

        int idx = 1;
        while (!priorityQueue.isEmpty()){
            Lecture lecture = priorityQueue.poll();
            if (!aq.isEmpty()&&isCan(aq.peek().getLecture(), lecture)){
                Allocation allocation = aq.poll();
                aq.offer(new Allocation(lecture, allocation.getNum()));
                result[lecture.getNum()] = allocation.getNum();
            }else{
                result[lecture.getNum()] = idx;
                aq.offer(new Allocation(lecture, idx));
                idx++;
            }
        }
        System.out.println(idx-1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<N; i++){
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }

    private boolean isCan(Lecture l1, Lecture l2) {
        if (l1.getEndTime()<=l2.getStartTime()){
            return true;
        }else{
            return false;
        }
    }
}
class Lecture{
    private final int num;
    private final int startTime;
    private final int endTie;


    public Lecture(int num, int startTime, int endTie) {
        this.num = num;
        this.startTime = startTime;
        this.endTie = endTie;
    }

    public int getNum() {
        return num;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTie;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "num=" + num +
                ", startTime=" + startTime +
                ", endTie=" + endTie +
                '}';
    }
}
class Allocation{
    private final Lecture lecture;
    private final int num;

    public Allocation(Lecture lecture, int num) {
        this.lecture = lecture;
        this.num = num;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "lecture=" + lecture +
                ", roomNum=" + num +
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