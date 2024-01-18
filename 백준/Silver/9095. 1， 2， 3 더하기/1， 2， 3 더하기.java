import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// 5 -> dp[4] + dp[3] + dp[2]
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();

		for (int i = 0; i < T; i++) {
			int n = scan.nextInt();

			int[] dp = new int[n + 4];

			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			dp[4] = 7;

			for (int j = 5; j <= n; j++) {
				dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
			}

			System.out.println(dp[n]);
		}
	}
}