import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new sol();
    }
}
class sol{
    Buf buf = new Buf();
    sol() throws IOException {
        int n = buf.getInt();
        StringBuffer sb=new StringBuffer();
        for (int i = 0 ; i<n; i++){
            int tmp = buf.getInt();
            String tmpS = buf.get();
            for (int j = 0; j<tmpS.length(); j++){
                for (int k = 0 ; k<tmp; k++){
                    sb.append(tmpS.charAt(j));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
class Buf{
    BufferedReader br;
    StringTokenizer st;
    Buf() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    public String get(){
        while(st==null||!st.hasMoreElements()){
            try{
                st= new StringTokenizer(br.readLine());
            }catch(IOException e){
                return null;
            }
        }
        return st.nextToken();
    }
    public Integer getInt(){
        return Integer.parseInt(get());
    }
}