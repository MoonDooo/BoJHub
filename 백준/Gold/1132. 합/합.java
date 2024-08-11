import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        new Sum();
    }
}
class Sum{
    private int N;
    private Map<Character, Long> alp = new HashMap<>();
    private Buf buf = new Buf();
    private final boolean[] isNotZero = new boolean[26];
    public Sum(){
        N = buf.nextInt();
        for(int i = 0; i<N; i++){
            String input  = buf.next();
            for(int j = 0; j<input.length(); j++){
                Character c = input.charAt(j);
                alp.put(c, alp.getOrDefault(c, 0L) + (long) Math.pow(10, input.length() - (j + 1))
                );
                if(j==0){
                    isNotZero[getAlpIdx(c)] = true;
                }
            }
        }

        Long result = 0L;
        for(int i = 10-alp.size(); i<10; i++){
            if(i==0){
                Character c = alp.keySet().stream().filter(a->!isNotZero[getAlpIdx(a)]).min((a,b)->alp.get(a).compareTo(alp.get(b))).get();
                alp.remove(c);
            }else{
                Character c = alp.keySet().stream().min((a,b)->alp.get(a).compareTo(alp.get(b))).get();
                result += alp.get(c)*i;
                alp.remove(c);
            }
        }
        System.out.println(result);
    }
    private int getAlpIdx(char c){
        return (int)(c-'A');
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

}