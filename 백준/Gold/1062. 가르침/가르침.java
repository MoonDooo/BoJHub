import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new Teaching();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class Teaching{
	private int wordNum;
	private int teachingAlpabetNum;
	private int result;
	private String[] input;
	private boolean[] isAlreadyTeachingAlp;
	public Teaching() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		wordNum = Integer.parseInt(st.nextToken());
		teachingAlpabetNum = Integer.parseInt(st.nextToken());
		if(teachingAlpabetNum<5	) {
			System.out.println(0);
			return;
		}else if(teachingAlpabetNum==26) {
			System.out.println(wordNum);
			return;
		}
		
		isAlreadyTeachingAlp = new boolean[26];
		InitTeacingAlp();
		teachingAlpabetNum -= 5;
		
		input = new String[wordNum];
		for(int i = 0; i<wordNum; i++) {
			input[i] = br.readLine();
		}
		
	
		
		FindMaxWordNumCanTeacking(0,0);
		System.out.println(result);
		
	}
	public void InitTeacingAlp() {
		isAlreadyTeachingAlp['a'-'a'] =true;
		isAlreadyTeachingAlp['n'-'a'] =true;
		isAlreadyTeachingAlp['t'-'a'] =true;
		isAlreadyTeachingAlp['i'-'a'] =true;
		isAlreadyTeachingAlp['c'-'a'] =true;
	}
	public void FindMaxWordNumCanTeacking(int idx, int AlpNum) {
		if(idx == teachingAlpabetNum) {
			int count = 0;
			for(int i = 0; i<wordNum; i++) {
				boolean isCanLearn = true;
				for(int j = 0; j<input[i].length(); j++) {
					if(!isAlreadyTeachingAlp[input[i].charAt(j)-'a']) {
						isCanLearn = false;
						break;
					}
				}
				if(isCanLearn) {
					count++;
				}
			}
			result = Math.max(result, count);
			return;
		}
		
		for(int i = AlpNum; i<26;i++) {
			if(!isAlreadyTeachingAlp[i]) {
				isAlreadyTeachingAlp[i] = true;
				FindMaxWordNumCanTeacking(idx+1, i+1);
				isAlreadyTeachingAlp[i] = false;
			}
		}
	}
}
