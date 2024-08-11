import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		try{
			new FindInputValue();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class FindInputValue{
	private long N;
	private long P;
	private long Q;
	private HashMap<Long, Long> map;
	public FindInputValue() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		map = new HashMap<>();
		N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());
		
		System.out.println(SequenceNofValue(N));
	}
	public long SequenceNofValue(long n) {
		if(n==0) {
			return 1;
		}
		if(map.containsKey(n))return map.get(n);
		else{
			long hashmapInputValue = SequenceNofValue(n/P)+SequenceNofValue(n/Q);
			map.put(n, hashmapInputValue);
			return hashmapInputValue;
		}
	}
}