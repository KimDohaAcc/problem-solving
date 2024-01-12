import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int T = scan.nextInt();
		for (int i = 0; i < T; i++) {
			// 층
			int k = scan.nextInt();
			// 호수
			int n = scan.nextInt();

			long[][] dp = new long[k + 1][n + 1];

			// 0층의 i호에는 i명이 산다
			for (int j = 1; j <= n; j++) {
				dp[0][j] = j;
			}

			// 같은 층 앞 호수까지 더한 거랑
			// 내 아래층 인원이랑 더하면 지금 호수의 인원을 구할 수 있음
			for (int j = 1; j <= k; j++) {
				for (int l = 1; l <= n; l++) {
					dp[j][l] = dp[j - 1][l] + dp[j][l - 1];
				}
			}

			System.out.println(dp[k][n]);
		}
	}
}