import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] land = new int[N][M];
        for (int i = 0; i < N; i++) {
            land[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][] dp = new int[N][M];
        int[][] temp = new int[2][M];
        dp[0][0] = land[0][0];

        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i-1] + land[0][i];
        }

        for (int i = 1; i < N; i++) {
            // temp 배열 초기화
            temp[0][0] = dp[i-1][0] + land[i][0];
            for (int j = 1; j < M; j++) {
                temp[0][j] = Math.max(temp[0][j - 1], dp[i - 1][j]) + land[i][j];
            }

            temp[1][M-1] = dp[i - 1][M-1] + land[i][M-1];
            for (int j = M-2; j >= 0; j--) {
                temp[1][j] = Math.max(temp[1][j + 1], dp[i - 1][j]) + land[i][j];
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(temp[0][j], temp[1][j]);
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}