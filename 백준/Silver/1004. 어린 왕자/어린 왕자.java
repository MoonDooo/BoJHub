import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    private Buf buf = new Buf();
    private int t;
    public Sol(){
        t = buf.readInt();
        for(int i = 0; i<t; i++){
            int count = 0;
            Point start = new Point(buf.readInt(), buf.readInt());
            Point end = new Point(buf.readInt(), buf.readInt());
            int n = buf.readInt();
            for(int j =0; j<n; j++){
                Point p = new Point(buf.readInt(), buf.readInt());

                int ratio = buf.readInt();
                count+=isIn(start, p, ratio)^isIn(end, p, ratio)?1:0;
            }
            System.out.println(count);
        }
    }
    boolean isIn(Point p1, Point p2, int ratio){
        if(Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2))<=ratio){
            return true;
        }
        return false;
    }
}
class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Buf{
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;
    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }catch (IOException e){
                return null;
            }
        }
        return st.nextToken();
    }
    public Integer readInt(){
        return Integer.parseInt(read());
    }
}