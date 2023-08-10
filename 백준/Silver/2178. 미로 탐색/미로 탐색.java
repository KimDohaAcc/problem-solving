import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[] dy = { 0, 1, 0, -1 };
	public static int[] dx = { 1, 0, -1, 0 };
	public static int N;
	public static int M;
	public static String[][] arr;
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new String[N][M];
		for (int i = 0; i < N; i++) {
			String[] splitStr = br.readLine().split("");

			for (int j = 0; j < M; j++) {
				arr[i][j] = splitStr[j];
			}
		}

		int y = 0;
		int x = 0;
		int[][] cnt = new int[N][M];
		Queue<String> queue = new LinkedList<String>();

		cnt[y][x] = 1;
		queue.offer(y + "," + x);

		Loop1: while (true) {
			String[] str = queue.poll().split(",");
			y = Integer.parseInt(str[0]);
			x = Integer.parseInt(str[1]);

			for (int i = 0; i < 4; i++) {
				int tempY = y + dy[i];
				int tempX = x + dx[i];

				if (tempY < N && tempX < M && tempY >= 0 && tempX >= 0 && arr[tempY][tempX].equals("1")
						&& cnt[tempY][tempX] == 0) {
					cnt[tempY][tempX] = cnt[y][x] + 1;
					if (tempY == N - 1 && tempX == M - 1) {
						break Loop1;
					}
					queue.offer(tempY + "," + tempX);
				}
			}
		}

		System.out.println(cnt[N - 1][M - 1]);
	}
}