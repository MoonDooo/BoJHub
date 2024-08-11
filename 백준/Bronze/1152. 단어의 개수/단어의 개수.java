import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new sol();
    }
}
class sol{
    sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int i = 0;
        while(stringTokenizer.hasMoreTokens()){
            stringTokenizer.nextToken();
            i++;
        }
        System.out.println(i);
    }
}