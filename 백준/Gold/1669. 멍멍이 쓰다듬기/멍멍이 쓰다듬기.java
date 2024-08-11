import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new PettingDog();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class PettingDog{
	int result;
	public PettingDog ()throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int heightDifference = y-x;
		if(heightDifference==0) {
			System.out.println(0);
			return;
		}
		
		
		result = 0;
		boolean isSecond = false;
		for(int tmp = 1; 0<heightDifference;result++) {
			heightDifference -= tmp;
			
			if(isSecond) {
				tmp++;
				isSecond = false;
			}
			else {
				isSecond=true;
			}
		}
		
		System.out.println(result);
	}
}