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
    int[][] dp;
    int N;
    int W;
    private XY[] input;
    private XY[][] check;
    public Sol() {
        N = buf.readInt();
        W = buf.readInt();
        input = new XY[W+1];
        for (int i = 0; i<W; i++){
            XY xy = new XY(buf.readInt(), buf.readInt());
            input[i+1] = xy;
        }
        dp = new int[W+1][W+1];
        check= new XY[W+1][W+1];
        for (int i = 0; i<W+1; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j<W+1; j++){
                check[i][j] = new XY(0,0);
            }
        }

        dp[0][0] = 0;
        dp[1][0] = distanceFirstPolice(1,0);
        check[1][0].setXY(0,0);
        dp[0][1] = distanceSecondPolice(1,0);
        check[0][1].setXY(0,0);
        for (int i = 1; i<W;i++){
            for (int j =0; j<i; j++){
                if (dp[i][j]+distanceFirstPolice(i+1,i)< dp[i+1][j]){
                    dp[i+1][j] = dp[i][j]+distanceFirstPolice(i+1,i);
                    check[i+1][j].setXY(i,j);
                }
                if (dp[i][j]+distanceSecondPolice(i+1,j)<dp[i][i+1]){
                    dp[i][i+1] = dp[i][j]+distanceSecondPolice(i+1,j);
                    check[i][i+1].setXY(i, j);
                }
                if (dp[j][i]+distanceSecondPolice(i+1, i)<dp[j][i+1]){
                    dp[j][i+1] = dp[j][i]+distanceSecondPolice(i+1, i);
                    check[j][i+1].setXY(j,i);
                }
                if (dp[j][i]+distanceFirstPolice(i+1,j)<dp[i+1][i]){
                    dp[i+1][i] = dp[j][i]+distanceFirstPolice(i+1,j);
                    check[i+1][i].setXY(j,i);
                }
            }
        }
        int cin = Integer.MAX_VALUE;
        XY minXY = new XY(W,W);
        for (int i = 0; i<W;i++){
            if (dp[i][W]<cin){
                cin = dp[i][W];
                minXY.setXY(i, W);
            }
            if (dp[W][i]<cin){
                cin = dp[W][i];
                minXY.setXY(W, i);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(cin).append(route(minXY).reverse());
        System.out.println(sb);
    }

    public int distanceFirstPolice(int i, int j){
        XY xy;
        if (j==0){
            xy = new XY(1,1);
        }else{
            xy = input[j];
        }
        return Math.abs(input[i].getY()-xy.getY())+Math.abs(input[i].getX()-xy.getX());
    }

    public int distanceSecondPolice(int i, int j){
        XY xy;
        if (j==0){
            xy = new XY(N,N);
        }else{
            xy = input[j];
        }
        return Math.abs(input[i].getY()-xy.getY())+Math.abs(input[i].getX()-xy.getX());
    }

    public StringBuilder route(XY xy){
        StringBuilder sb = new StringBuilder();
        int w=W;
        while(w!=0){
            if (xy.getX()==w)sb.append("1").append("\n");
            else if (xy.getY()==w)sb.append("2").append("\n");
            w--;
            xy = check[xy.getX()][xy.getY()];
        }
        return sb;
    }
}
class XY{
    int X,Y;

    public XY(int x, int y) {
        X = x;
        Y = y;
    }
    public void setXY(int x, int y){
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public String toString() {
        return "XY{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
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