import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	static int[][][] map;
	static int color = 1;

	static List<int[]> zeroList = new ArrayList<>();
	static List<int[]> wallList = new ArrayList<>();
	static boolean[][] visited;

	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 세로
		N = Integer.parseInt(st.nextToken());
		// 가로
		M = Integer.parseInt(st.nextToken());

		// 0 : 맵, 1 : 0 개수, 2: 색깔
		map = new int[N][M][3];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String[] split = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j][0] = Integer.parseInt(split[j]);
				map[i][j][1] = map[i][j][0];
				map[i][j][2] = map[i][j][0];

				if (map[i][j][0] == 0) {
					zeroList.add(new int[]{i, j});
				} else {
					wallList.add(new int[]{i, j});
					map[i][j][0] = 1000001;
				}
			}
		}

		for (int i = 0; i < zeroList.size(); i++) {
			int[] arr = zeroList.get(i);

			if (visited[arr[0]][arr[1]]) {
				continue;
			}

			breakWall(arr);
//			print();
		}

		for (int i = 0; i < wallList.size(); i++) {
			List<Integer> colorList = new ArrayList<>();
			int[] arr = wallList.get(i);
			int cnt = 1;
			for (int j = 0; j < 4; j++) {
				int ny = arr[0] + dy[j];
				int nx = arr[1] + dx[j];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
					continue;
				}

				if (map[ny][nx][0] == 0 && !colorList.contains(map[ny][nx][2])) {
					cnt += map[ny][nx][1];
					colorList.add(map[ny][nx][2]);
				}
			}

			map[arr[0]][arr[1]][0] = cnt;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j][0] % 10);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j][1]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static void breakWall(int[] arr) {
		int cnt = 1;
		Queue<int[]> queue = new ArrayDeque<>();

		queue.offer(arr);
		visited[arr[0]][arr[1]] = true;
		Queue<int[]> checkList = new ArrayDeque<>();

		while (!queue.isEmpty()) {

			int[] now = queue.poll();
			checkList.offer(now);

			for (int i = 0; i < 4; i++) {
				int ny = now[0] + dy[i];
				int nx = now[1] + dx[i];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
					continue;
				}

//				System.out.println("cnt : " + map[ny][nx]);

				if (!visited[ny][nx] && map[ny][nx][0] == 0) {
					visited[ny][nx] = true;
					cnt++;
					int[] nextIdx = new int[]{ny, nx};
					queue.offer(nextIdx);
				}
			}
		}
		while (!checkList.isEmpty()) {
			int[] poll = checkList.poll();
			map[poll[0]][poll[1]][1] = cnt;
			map[poll[0]][poll[1]][2] = color;
		}

		color++;
	}
}