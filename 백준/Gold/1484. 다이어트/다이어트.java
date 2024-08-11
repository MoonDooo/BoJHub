import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @백준 1484번
 * @author MoonDooo
 */
public class Main {
	public static void main(String[] args) {
		try {
			new Diet();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
class Diet{
	private int input;
	private ArrayList<Integer> divisor;
	private ArrayList<Integer> output;
	public Diet() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = Integer.parseInt(br.readLine());
		
		divisor = new ArrayList<>();
		output = new ArrayList<>();
		FindDivisor();
		
		for(int i = 0; i < divisor.size(); i++) {
			int inputDiv = input/divisor.get(i);
			if((inputDiv+divisor.get(i))%2==0) {
				output.add((inputDiv+divisor.get(i))/2);
			}
		}
		
		if(output.size()==0) {
			System.out.println(-1);
			return;
		}
		Collections.sort(output);
		for(int i = 0; i<output.size(); i ++) {
			System.out.println(output.get(i));
		}
	}
	public void FindDivisor() {
		double sqrt = Math.sqrt(input);
		for(double i = 1; i < sqrt;i++) {
			if(input%i==0) {
				divisor.add((int)i);
			}
		}
	}
}
