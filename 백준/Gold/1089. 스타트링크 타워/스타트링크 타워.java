import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		try {
			new StartLinkTower();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class StartLinkTower{
	private int floorDivide10;
	private boolean[][][] bulb;
	private ArrayList<Integer>[] array;
	
	public StartLinkTower() throws IOException{
		Input();
		if(!InitArray()) {
			System.out.println(-1);
			return;
		}
		System.out.println(CalculrateAllCaseAvg());
		
		for(int i = 0; i<floorDivide10; i++) {
			
		}
	}
	
	public void Input()	throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		floorDivide10 = Integer.parseInt(br.readLine());
		
		bulb = new boolean[floorDivide10][3][5];
		for(int i = 0; i<5; i++	) {
			String input = br.readLine();
			for(int j = 0; j<floorDivide10; j++) {
				for(int k = 4*j; k<(4*j+3); k++	) {
					if(input.charAt(k)=='#') {
						bulb[j][k%4][i] = true;
					}
				}
			}
		}
		
		array = new ArrayList[floorDivide10];
		for(int i = 0; i<floorDivide10; i++) {
			array[i] = new ArrayList<>();
		}
	}
	public boolean InitArray() {
		for(int i = 0; i<floorDivide10; i++) {
			array[i].add(8);
			if(IsCannotAll(i)) {
				System.out.println(-1);
				return false; 
			}
			if(!IsCannotZero(i)) {
				array[i].add(0);
			}
			if(!IsCannotOne(i)) {
				array[i].add(1);
			}
			if(!IsCannotTwo(i)) {
				array[i].add(2);
			}
			if(!IsCannotThree(i)) {
				array[i].add(3);
			}
			if(!IsCannotFour(i)) {
				array[i].add(4);
			}
			if(!IsCannotFive(i)) {
				array[i].add(5);
			}
			if(!IsCannotSix(i)) {
				array[i].add(6);
			}
			if(!IsCannotSeven(i)) {
				array[i].add(7);
			}
			if(!IsCannotNine(i)) {
				array[i].add(9);
			}
		}
		return true;
	}
	
	public double CalculrateAllCaseAvg() {
		double result = 0;
		for(int i = 0; i<floorDivide10; i++) {
			double tmp = 0;
			for(int j = 0; j<array[i].size(); j++) {
				tmp += array[i].get(j)*Math.pow(10,floorDivide10-i-1);
			}
			result += tmp/array[i].size();
		}
		return result;
	}

	public boolean IsCannotAll(int idx) {
		if(bulb[idx][1][1]||bulb[idx][1][3]) {
			return true;
		}
		return false;
	}
	
	public boolean IsCannotZero(int idx) {
		for(int i =1; i<=3; i++) {
			if(bulb[idx][1][i]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean IsCannotOne(int idx) {
		for(int i = 0; i<=1; i++	) {
			for(int j = 0; j<=4; j++	) {
				if(bulb[idx][i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean IsCannotTwo(int idx) {
		if(bulb[idx][0][1]||bulb[idx][2][3]) {
			return true;
		}
		return false;
	}
	
	public boolean IsCannotThree(int idx) {
		if(bulb[idx][0][1]||bulb[idx][0][3]) {
			return true;
		}
		return false;
	}
	
	public boolean IsCannotFour(int idx) {
		if(bulb[idx][1][0]) {
			return true;
		}
		for(int i = 0; i <= 1; i++) {
			for(int j = 3; j<= 4; j++	) {
				if(bulb[idx][i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean IsCannotFive(int idx) {
		if(bulb[idx][2][1]||bulb[idx][0][3]) {
			return true;
		}
		return false;
	}
	
	public boolean IsCannotSix(int idx) {
		if(bulb[idx][2][1]) {
			return true;
		}
		return false;
	}
	
	public boolean IsCannotSeven(int idx) {
		for(int i = 0; i <= 1; i++	) {
			for(int j = 1; j<=4; j++	) {
				if(bulb[idx][i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean IsCannotNine(int idx) {
		if(bulb[idx][0][3]) {
			return true;
		}
		return false;
	}
}