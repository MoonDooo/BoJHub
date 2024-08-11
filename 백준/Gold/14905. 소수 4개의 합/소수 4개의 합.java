import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        new fourPrimeSum();
    }
}
class fourPrimeSum{
    private boolean[] isPrime;
    private List<Integer> prime;
    private int[] result;
    public fourPrimeSum() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isPrime = new boolean[100000001];
        prime = new ArrayList<>();
        Arrays.fill(isPrime, true);
        setPrime();
        result = new int[4];
        String input;
        while((input = br.readLine())!=null){
            int N = Integer.parseInt(input);
            if(N<8){
                System.out.println("Impossible.");
                continue;
            }else{
                result[0] = 2;
                if(N%2==0){
                    result[1] = 2;
                    gold(N-4);
                }else{
                    result[1] = 3;
                    gold(N-5);
                }
            }
            for(int i = 0; i<4; i++){
                System.out.print(result[i]+" ");
            }
            System.out.println();
        }

    }

    private void gold(int N) {
        for(int i=0; i<prime.size(); i++) {
            for(int j=0; j<prime.size(); j++) {

                if(prime.get(i) + prime.get(j) == N) {
                    result[2] = prime.get(i);
                    result[3] = prime.get(j);
                    return;
                }else if(N<prime.get(i) + prime.get(j)) {
                    break;
                }
            }
        }
    }

    public void setPrime(){
        for(int i = 2; i*i<=100000000;i++){
            if (!isPrime[i])continue;
            for(int j = i*i; j<=100000000; j+=i){
                isPrime[j] = false;
            }
        }

        for(int i = 2; i<=100000000; i++){
            if (isPrime[i]){

                prime.add(i);
            }
        }
    }
}
