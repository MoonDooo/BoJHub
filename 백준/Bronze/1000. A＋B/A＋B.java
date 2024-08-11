import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Plus();
	}	
}
class Plus {
	int a, b;
	Scanner sc = new Scanner(System.in);
	Plus(){
		a = sc.nextInt();
		b = sc.nextInt();
		plus();
	}
	void plus(){
		System.out.println(a+b);
	}
}