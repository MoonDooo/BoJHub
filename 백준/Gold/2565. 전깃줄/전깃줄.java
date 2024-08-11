import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new TwistedElectricWire();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class TwistedElectricWire{
	private int[] array;
	private int[] dp;
	private int num;
	public TwistedElectricWire() throws IOException{
		array = new int[501];
		dp = new int[501];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		for(int i = 0; i<num; i++) {
			StringTokenizer st=  new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			array[idx] = n;
		}
		int max = 0;
		for(int i = 0; i<501; i++) {
			if(array[i]==0) {
				continue;
			}
			int maxInIdx = 0;
			for(int j = 0; j<i; j++) {
				if(array[j]<array[i]&&array[j]!=0) {
					maxInIdx = Math.max(maxInIdx, dp[j]);
				}
			}
			dp[i] = maxInIdx+1;
			max = Math.max(max, dp[i]);
		}
		System.out.println(num - max);
	}
}
