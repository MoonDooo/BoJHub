
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        new Contact();
    }
}
class Contact{
    private final Buf buf = new Buf();
    private int T;
    String input;
    public Contact(){
        T = buf.nextInt();
        for (int  i =0 ; i<T; i++){
            boolean b = calculating();
            if (b) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private boolean calculating() {
        input = buf.next();
        for (int j = 0; j<input.length();){
            if (isOne(j)){
                int zeroCount = 0;
                while(isZero(j+1)){
                    zeroCount++;
                    j++;
                }
                if (1<zeroCount){
                    int oneCount = 0;
                    while(isOne(j+1)){
                        j++;
                        oneCount++;
                    }
                    if (0<oneCount){
                        ++j;
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }else if (isZero(j)){
                if (isOne(++j)){
                    ++j;
                }else{
                    if (0<=j-2&0<=j-3&&input.charAt(j-2)=='1'&&input.charAt(j-3)=='1'){
                        j-=2;
                        continue;
                    }
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean isZero(int idx){
        if (idx<input.length()){
            return input.charAt(idx) == '0';
        }else{
            return false;
        }
    }
    public boolean isOne(int idx){
        if (idx<input.length()){
            return input.charAt(idx) == '1';
        }else{
            return false;
        }
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