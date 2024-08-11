
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new VectorMatch();
    }
}
class VectorMatch{
    private final Buf buf = new Buf();
    private int pointNum;
    private double result;
    private Point[] pointList;
    private boolean[] isStartedPoint;

    public VectorMatch(){
        int testcase = buf.nextInt();
        for(int i = 0; i< testcase; i++){
            result = Double.MAX_VALUE;
            initData();
            calculating(0, 0);
            System.out.println(result);
        }
    }

    private void initData() {
        pointNum = buf.nextInt();
        pointList = new Point[pointNum];
        isStartedPoint = new boolean[pointNum];
        for (int j = 0; j<pointNum; j++){
            pointList[j] = new Point(buf.nextInt(), buf.nextInt());
        }
    }
    private void calculating(int idx, int deep){
        if(deep==pointNum/2){
            Point vec = new Point();
            for(int i = 0; i < pointNum; i++){
                if(isStartedPoint[i])vec.setXY(vec.getX()-pointList[i].getX(), vec.getY()-pointList[i].getY());
                else vec.setXY(vec.getX()+pointList[i].getX(), vec.getY()+pointList[i].getY());
            }
            result = Math.min(Math.sqrt(vec.getX()*vec.getX() + vec.getY()*vec.getY()), result);
            return;
        }

        for(int i = idx; i<pointNum; i++){
            isStartedPoint[i] = true;
            calculating(i+1, deep+1);
            isStartedPoint[i] = false;
        }
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
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }
    int nextInt(){
        return Integer.parseInt(next());
    }

}
class Point{
    private long x;
    private long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void setXY(long x, long y) {
        this.x = x;
        this.y = y;
    }

}