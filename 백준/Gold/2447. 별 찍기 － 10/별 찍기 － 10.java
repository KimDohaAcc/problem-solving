import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static int cnt = 1;
	public static int N;
	public static String[][] stars;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = scan.nextInt();
		stars = new String[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(stars[i], "*");
		}

		makeStar(3);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(stars[i][j]);
			}
			sb.append('\n');
		}

		System.out.println(sb.toString());
	}

	public static void makeStar(int n) {
		if (Math.pow(3, cnt - 1) == N) {
			return;
		}

		int range = (int) Math.pow(3, cnt - 1);
		for (int i = range; i < N; i += (int) Math.pow(3, cnt)) {
			for (int j = range; j < N; j += (int) Math.pow(3, cnt)) {
				int y = (int) Math.pow(3, cnt - 1);
				int x = (int) Math.pow(3, cnt - 1);

				for (int k = 0; k < y; k++) {
					for (int h = 0; h < x; h++) {
						stars[i + k][j + h] = " ";
					}
				}
			}
		}

		n = (int) Math.pow(3, ++cnt);
		makeStar(n);
	}
}