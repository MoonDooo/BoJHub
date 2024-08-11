import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Theater();
    }
}
class Theater{
    private Buf buf = new Buf();
    private int N;
    private int max;
    private boolean[] isVisited;
    private List<Byte> result = new ArrayList<>();
    private Queue<Byte> q = new LinkedList<>();

    public Theater(){
        N = buf.nextInt();
        max = (int)Math.pow(2, N);
        isVisited = new boolean[1<<N];
        calculating(0, 0);
        System.out.println(result.size()-1);
        result.forEach(System.out::println);
    }
    public boolean calculating(int input, int d){
        if (d==max&&input == 0){
            if (result.size()<q.size()){
                result = new ArrayList<>(q);
            }
            return true;
        }

        for(int i = 0; i<N; i++){
            int tmp = input^(1<<i);
            if (!isVisited[tmp]){
                if (tmp!=0) isVisited[tmp] = true;
                q.add((byte) (i+1));
                if (calculating(tmp, d+1)) {
                    return true;
                }else {
                    q.remove();
                }
            }
        }

        return false;
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