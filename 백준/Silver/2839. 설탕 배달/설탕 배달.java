import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int cntFive = 0;
		int cntThree = 0;

		if (N / 5 > 0) {
			cntFive = N / 5;
			N %= 5;
		}

		while (true) {
			int n = N;
			if (n / 3 > 0) {
				cntThree = n / 3;
				n %= 3;
			}

			if (N == 0 || n == 0) {
				System.out.println(cntFive + cntThree);
				break;
			}

			if (cntFive == 0) {
				System.out.println(-1);
				break;
			}

			if (cntFive > 0) {
				N += 5;
				cntFive--;
			}

		}

	}
}