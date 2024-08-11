import java.util.*;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		Boolean b = (a%4==0) && (a%100 != 0);
		if(b||a%400==0) {
			System.out.println("1");
		}
		else {
			System.out.println("0");
		}
	}
}
