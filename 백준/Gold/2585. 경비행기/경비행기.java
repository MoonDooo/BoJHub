import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    private int N, M;
    private final Buf buf = new Buf();
    private final List<XY> input = new ArrayList<>();
    private final XY endPoint = new XY(10000, 10000);
    private boolean[] isVisited;
    public Sol(){
        N = buf.readInt();
        M = buf.readInt();
        input.add(new XY(0,0));
        for (int i = 0; i < N; i++){
            input.add(new XY(buf.readInt(), buf.readInt()));
        }
        input.add(endPoint);

        int right = 14143;
        int left = 0;

        while(left<right){
            isVisited = new boolean[input.size()];

            int mid = ((right+left)/2);
            if (inCan(mid*10, 0, 0)){
                right = mid;
            }else{
                left = mid+1;
            }
        }

        System.out.println(right);

    }
    public boolean inCan(int mid, int idx,int dept){
        if (M<dept||isVisited[idx])return false;
        isVisited[idx]=true;
        XY xy = input.get(idx);
        for (int i = 0; i<input.size(); i++){
            if (uclid(xy.getX(), input.get(i).getX(), xy.getY(), input.get(i).getY())<=(double)mid){
                if (input.get(i).equals(endPoint))return true;
                if (inCan(mid, i, dept+1))return true;
            }
        }
        return false;
    }

    public double uclid(int x1, int x2, int y1, int y2){
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

}

class XY {
    private int x, y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
class Buf{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public String read(){
        while(st==null||!st.hasMoreElements()){
            try{
                st = new StringTokenizer(br.readLine());
            }catch(IOException e){
                return null;
            }
        }
        return st.nextToken();
    }
    public Integer readInt(){
        return Integer.parseInt(read());
    }

    public Long readLong(){
        return Long.parseLong(read());
    }
}