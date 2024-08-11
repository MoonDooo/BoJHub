	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.StringTokenizer;
	
	/**
	 * @Baejun 1010
	 * @author MoonDooo
	 */
	public class Main {
		public static void main(String[] args) {
			try {
				new Bridge();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	class Bridge{
		private int westSite;
		private int eastSite;
		private int[][] dynamic;
		private int testCounter;
		public Bridge() throws IOException{
			dynamic = new int[30][30];
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			testCounter = Integer.parseInt(br.readLine());
			for(int i = 0; i<testCounter; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				westSite = Integer.parseInt(st.nextToken());
				eastSite = Integer.parseInt(st.nextToken());
				System.out.println(nCr(eastSite, westSite));
			}
		}
		public int nCr(int n, int r) {
			if(dynamic[n][r]>0) {
				return dynamic[n][r];
			}
			else if(n==r||r==0) {
				return dynamic[n][r] = 1;
			}
			return dynamic[n][r] = nCr(n-1,r-1) + nCr(n-1,r);
		}
	}
