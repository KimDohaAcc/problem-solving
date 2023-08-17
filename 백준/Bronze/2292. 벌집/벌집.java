import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();

		int[] twoToSeven = { 2, 3, 4, 5, 6, 7 };
		int max = Integer.MAX_VALUE;
		int maxCnt = Integer.MAX_VALUE;

		// 직선 거리 찾기
		Loop: for (int i = 0; i < 6; i++) {
			int n = 1;
			int cnt = 1;
			int save = twoToSeven[i] - 1;

			while (n < N) {
				if (cnt == 1) {
					n = twoToSeven[i];
					cnt++;
					if (check(n, cnt, N)) {
						break Loop;
					}
				}

				save += 6;
				n += save;
				cnt++;
			}

			if (check(n, cnt, N)) {
				break Loop;
			}

			max = Math.min(n, max);
			maxCnt = Math.min(cnt, maxCnt);

			// 직선 아님
			if (i == 5) {
				System.out.println(maxCnt);
				break;
			}
		}
	}

	public static boolean check(int n, int cnt, int N) {
		if (n == N) {
			System.out.println(cnt);
			return true;
		}

		return false;
	}
}