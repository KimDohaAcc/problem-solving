import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		int[] dp = new int[n + 6];
		dp[1] = 1;
		dp[2] = 3;
		dp[3] = 5;

		for (int i = 4; i <= n; i++) {
			dp[i] = (2 * dp[i - 2] + dp[i - 1]) % 10007;
		}

		System.out.println(dp[n]);

	}
}