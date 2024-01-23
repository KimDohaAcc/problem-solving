import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	static int N;
	static List<Integer>[] block;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 학생 수
		N = Integer.parseInt(st.nextToken());
		// 최대 블록 개수
		int M = Integer.parseInt(st.nextToken());
		// 만들어야 하는 높이
		int H = Integer.parseInt(st.nextToken());

		// N명의 학생으로 높이 H를 만들 수 있는 경우의 수
		dp = new int[N + 1][H + 1];

		block = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			block[i] = new ArrayList<>();
		}

		for (int i = 0; i <= N; i++) {
			// 높이 0을 만드는 경우는 1
			dp[i][0] = 1;
		}

		// 블록 초기화
		for (int i = 1; i <= N; i++) {
			int idx = i;
			Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(h -> {
				block[idx].add(h);
			});
		}

		// 학생 1명에 따라 만들 수 있는 전체 높이를 먼저 구해야 함
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= H; j++) {
				// i 학생이 가진 블럭을 탐색
				for (int x : block[i]) {
					// 만약 x의 높이가 만들고자 하는 i 높이보다 작거나 같다면
					if (j - x >= 0) {
						// [현재 학생 수 - 1][x와 더해서 높이 i를 만들 수 있는 수]
						dp[i][j] += dp[i - 1][j - x] % 10007;
					}
				}

				// 직전 학생까지 구한 높이에서 추가 블럭을 더하지 않은 경우도 포함해줌
				dp[i][j] += dp[i - 1][j] % 10007;
			}
		}

		System.out.println(dp[N][H] % 10007);
	}
}