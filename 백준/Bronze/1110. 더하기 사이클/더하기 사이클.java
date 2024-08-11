import java.util.*;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		final int b = a;
		int count = 0;
		int c, d;
		do {
			c = a/10; 
			d = a%10; 
			a = d*10 + ((c+d) % 10); 
			count++;
		}while(a != b);
		System.out.println(count);
	}
}
