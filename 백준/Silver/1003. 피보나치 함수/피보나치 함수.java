import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args){
        new Sol();
    }
}
class Sol{
    Buf buf = new Buf();
    private final List<Integer> numZero = new ArrayList<>();
    private final List<Integer> numOne = new ArrayList<>();
    private int T;
    public Sol(){
        T = buf.readInt();
        int idx = 0;
        for (int i = 0; i<T;i++){
            int k = buf.readInt();
            for (int j = idx; j<=k; j++){
                if (j==0){
                    numZero.add(1);
                    numOne.add(0);
                }else if (j==1){
                    numZero.add(0);
                    numOne.add(1);
                }else{
                    numZero.add(numZero.get(j-1)+numZero.get(j-2));
                    numOne.add(numOne.get(j-1)+numOne.get(j-2));
                }
            }
            idx = k+1;
            System.out.println(numZero.get(k) +" " + numOne.get(k));
        }
    }
}
class Buf{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    public String read(){
        while(st==null||!st.hasMoreElements()){
            try {
                st=new StringTokenizer(br.readLine());
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