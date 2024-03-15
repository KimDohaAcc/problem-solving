import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	/*
	 모든 지점에 대해서 목표지점까지의 거리를 구하기
	 -> 다익스트라
	 */
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n][m];
		int[] goal = new int[2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 2) {
					goal[0] = i;
					goal[1] = j;
				}
			}
		}

		int[][] dis = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					dis[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		boolean[][] visited = new boolean[n][m];
		Queue<int[]> pq = new ArrayDeque<>();
		dis[goal[0]][goal[1]] = 0;
		visited[goal[0]][goal[1]] = true;
		pq.offer(goal);

		while (!pq.isEmpty()) {
			int[] idx = pq.poll();

			for (int i = 0; i < 4; i++) {
				int ny = idx[0] + dy[i];
				int nx = idx[1] + dx[i];

				if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
					continue;
				}

				if (map[ny][nx] != 1) {
					continue;
				}

				if (dis[idx[0]][idx[1]] + 1 < dis[ny][nx]) {
					dis[ny][nx] = dis[idx[0]][idx[1]] + 1;
				}

				if (!visited[ny][nx]) {
					visited[ny][nx] = true;
					pq.offer(new int[]{ny, nx});
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (dis[i][j] == Integer.MAX_VALUE) {
					sb.append(-1);
				} else {
					sb.append(dis[i][j]);
				}
				sb.append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}
}