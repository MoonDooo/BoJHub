import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        new Change();
    }
}

class Change{
    private String input;
    private int K;
    private final Buf buf = new Buf();
    private Queue<String> queue= new LinkedList<>();
    private int result;

    public Change(){
        input = buf.next();
        K = buf.nextInt();
        queue.add(input);

        solve();

    }

    private void solve() {
        int currentK = 0;
        while(!queue.isEmpty()&currentK!=K){
            Set<String> isDuplicate = new HashSet<>();
            int size = queue.size();
            for (int i =0; i<size; i++){
                String qString = queue.poll();
                for (int j =0; j<input.length(); j++){
                    for (int k=0; k<input.length(); k++){
                        if (j==k)continue;
                        String tmp = swap(qString, j, k);
                        if (tmp.charAt(0)=='0'||isDuplicate.contains(tmp))continue;
                        else if (currentK+1 == K&&result<Integer.parseInt(tmp)){
                            result = Integer.parseInt(tmp);
                        }
                        queue.add(tmp);
                        isDuplicate.add(tmp);
                    }
                }
            }
            currentK++;

        }
        if (currentK!=K||result==0){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
    }

    private String swap(String s, int i, int j) {
        char[] chars = s.toCharArray();
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
        return new String(chars);
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