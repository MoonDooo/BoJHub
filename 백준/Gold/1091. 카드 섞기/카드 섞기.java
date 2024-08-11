import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        new Sol();
    }
}
class Sol{
    Buf buf = new Buf();
    List<List<Integer>> simul = new ArrayList<>();
    int[] p;
    int[] s;
    public Sol(){
        int N = buf.readInt();
        p = new int[N];
        s = new int[N];
        addArray(p);
        addArray(s);
        int result = 0;
        List<Integer> card = new ArrayList<>();
        for (int i = 0; i<N; i++){
            card.add(i%3);
        }
        List<Integer> card2 = new ArrayList<>(card);
        while(!isCycle(card, card2, result)){
            boolean b = true;
            for (int i = 0; i<N; i++){
                b=p[i]==card.get(i);
                if (!b)break;
            }
            if (b){
                System.out.println(result);
                return;
            }
            simul.add(new ArrayList<>(card));
            card = Arrays.stream(s).mapToObj(card::get).collect(Collectors.toList());
            result++;
        }
        System.out.println(-1);
    }

    private boolean isCycle(List<Integer> card, List<Integer> card2, int idx) {
        return card.equals(card2)&&0<idx;
    }

    private void addArray(int[] array){
        for (int i = 0; i<array.length ; i++){
            array[i] = buf.readInt();
        }
    }
}
class Buf{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
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