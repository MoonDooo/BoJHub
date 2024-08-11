import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new Lamp();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}

class Lamp{
	private int row, col, result;
	private String[] table;
	private HashMap<String, Integer> map;
	private ArrayList<Integer> possibleLightOnNumArray;
	public Lamp() throws IOException{
		Input();
	
		for(int i = 0; i<row; i++) {
			int zeroCount = 0;
			for(int j = 0; j<col;j++) {
				if(table[i].charAt(j)=='0') {
					zeroCount++;
				}
			}
			
			boolean isPossible = false;
			for(int j = 0; j<possibleLightOnNumArray.size(); j++) {
				if(zeroCount == possibleLightOnNumArray.get(j)) {
					isPossible = true;
					break;
				}
			}
			
			if(isPossible) {
				map.put(table[i], map.getOrDefault(table[i], 0)+1);
			}
		}
		
		result = 0;
		for(int tmp : map.values()) {
			result = Math.max(result, tmp);
		}
		
		System.out.println(result);
	}
	public void Input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		table = new String[row];
		
		for(int i = 0; i<row; i++) {
			table[i] = br.readLine();
		}
		map = new HashMap<>();
		
		int possibleLigntOnNum = Integer.parseInt(br.readLine());
		possibleLightOnNumArray = new ArrayList<>();
		while(possibleLigntOnNum>=0) {
			if(possibleLigntOnNum<=col) {
				possibleLightOnNumArray.add(possibleLigntOnNum);
			}
			possibleLigntOnNum -= 2;
		}
	}
}