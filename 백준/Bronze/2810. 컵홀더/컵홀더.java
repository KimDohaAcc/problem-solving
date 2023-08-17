import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int peelyo = 0;
		boolean Lcheck = false;

		char[] saram = scan.next().toCharArray();
		for (int i = 0; i < N; i++) {
			if (i < N - 1 && saram[i] == 'L' && saram[i + 1] == 'L') {
				Lcheck = true;
				peelyo++;
				i++;
			}
		}

		if (Lcheck) {
			peelyo--;
		}

		System.out.println(N - peelyo);
	}
}