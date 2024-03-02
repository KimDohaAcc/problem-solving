import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

    static int K;
    static int[] costArr;
    static int[][] dp;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(br.readLine());
            costArr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dp = new int[K + 1][K + 1];
            sum = new int[K + 1];

			for (int j = 1; j <= K; j++) {
				sum[j] = sum[j - 1] + costArr[j - 1];
			}
			combineChapter();
		}
	}

    static void combineChapter() {
        for (int i = 1; i <= K; i++) {
            for (int j = 1; i + j <= K; j++) {
                int end = i + j;
                dp[j][end] = Integer.MAX_VALUE;

                for (int k = j; k < end; k++) {
					dp[j][end] = Math.min(dp[j][end], dp[j][k] + dp[k + 1][end] + sum[end] - sum[j - 1]);
                }
            }
        }

		System.out.println(dp[1][K]);
    }
}