import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		try {
			new RightTriangle();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class RightTriangle {
	private ArrayList<Point> pointArray;
	private int pointNum;
	private int result;
	public RightTriangle() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		pointNum = Integer.parseInt(br.readLine());
		
		pointArray = new ArrayList<>();
		StringTokenizer st;
		for(int i =0; i<pointNum; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Point tmp = new Point(x,y);
			pointArray.add(tmp);
		}
		
		
		for(int i = 0; i<pointNum-2;i++) {
			Point first = pointArray.get(i);
			for(int j = i+1; j<pointNum-1; j++) {
				Point second = pointArray.get(j);
				long x = CalculateLength(first, second);
				for(int k = j+1; k<pointNum; k++) {
					Point third = pointArray.get(k);
					long y = CalculateLength(first, third);
					long z = CalculateLength(second, third);
					if(IsRightTriangle(x, y, z)) {
						result++;
					}
				}
			}
		}
		
		System.out.println(result);
		
	}
	
	public long CalculateLength(Point first, Point second) {
		long x = first.x-second.x;
		long y = first.y-second.y;
		return x*x+y*y;
	}
	
	public boolean IsRightTriangle(long x, long  y, long z) {
		if(x>y) {
			long tmp = x;
			x=y;
			y=tmp;
		}
		if(y>z) {
			long tmp = y;
			y = z;
			z = tmp;
		}
		
		if(x+y==z) {
			return true;
		}
		return false;
	}
}