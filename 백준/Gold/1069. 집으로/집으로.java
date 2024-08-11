import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new goHome();
    }
}

class goHome{
    int X, Y, D, T;

    public goHome() throws IOException {
        initData();
        
        double length = Math.sqrt(square(X) + square(Y));
     
        double result = length;

        if(D/T < 1){
            System.out.println(result);
            return;
        }
        double remainBy2Radius = length - 2*D;
        if(remainBy2Radius<0){
            result = Math.min(2*T, result);result = Math.min(T+Math.abs(D-length), result);}
        else if (remainBy2Radius ==0){
            result = Math.min(2*T, result);}else{
            int x = (int)remainBy2Radius/D;
            result = Math.min(T*(x+2) + (remainBy2Radius-(x*D)), result);
            result = Math.min(T*(x+3), result);        }
        System.out.println(result);
    }

    private double square(int x) {
        return Math.pow(x, 2);
    }

    private void initData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
    }
}