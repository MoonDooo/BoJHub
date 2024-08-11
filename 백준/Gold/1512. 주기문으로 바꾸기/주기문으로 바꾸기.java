import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		try {
			new ChangeToCycleDNA();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class ChangeToCycleDNA{
	private int cycleLength;
	private String input;
	private int[] dnaArray;
	private int result;
	
	public ChangeToCycleDNA() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cycleLength = Integer.parseInt(br.readLine());
		input = br.readLine();
		
		dnaArray = new int[4];//ATCG
		result = Integer.MAX_VALUE;
		
		for(int i=cycleLength; i>0; i--) {
			int resultWhenI = 0;
			for(int j = 0; j<i; j++) {
				Arrays.fill(dnaArray, 0);
				for(int k = j; k < input.length(); k+=i) {
					DNACount(input.charAt(k));
				}
				resultWhenI += FindChangeToCycleNum();
			}
			result = Math.min(result, resultWhenI);
		}
		
		System.out.println(result);
		
	}
	public void DNACount(char c) {
		switch(c) {
		case 'A':
			dnaArray[0]++;
			break;
		case 'T':
			dnaArray[1]++;
			break;
		case 'G':
			dnaArray[2]++;
			break;
		case 'C':
			dnaArray[3]++;
			break;
		}
	}
	public int FindChangeToCycleNum() {
		int max =0;
		int tmp =0;
		for(int i = 0; i<4; i++) {
			max = Math.max(max, dnaArray[i]);
			tmp += dnaArray[i];
		}
		return tmp-max;
	}
	
}
