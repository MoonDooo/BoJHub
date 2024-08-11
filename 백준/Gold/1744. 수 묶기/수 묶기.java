import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		try {
			new BindingNumber();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class BindingNumber{
	private int arrayNum;
	private ArrayList<Integer> plusArray;
	private ArrayList<Integer> minusArray;
	private int result;
	
	public BindingNumber() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arrayNum = Integer.parseInt(br.readLine());
		
		plusArray = new ArrayList<>();
		minusArray = new ArrayList<>();
		for(int i = 0; i<arrayNum;i++) {
			int tmp;
			if((tmp = Integer.parseInt(br.readLine()))>0) {
				plusArray.add(tmp);
			}else {
				minusArray.add(tmp);
			}
		}
			
		Collections.sort(plusArray, Collections.reverseOrder());
		Collections.sort(minusArray);
	
		result = 0;
		while(plusArray.size()!=0) {
			int a = plusArray.remove(0);
			if(plusArray.size()==0) {
				result += a;
				break;
			}
			int b = plusArray.remove(0);
			result += Math.max(a+b, a*b);
		}
		while(minusArray.size()!=0) {
			int a = minusArray.remove(0);
			if(minusArray.size()==0) {
				result += a;
				break;
			}
			int b = minusArray.remove(0);
			result += a*b;
		}

		System.out.println(result);
	}
}