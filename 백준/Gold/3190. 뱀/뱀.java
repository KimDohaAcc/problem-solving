import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int K = Integer.parseInt(br.readLine());
		int timer = 0;
		final int APPLE = 4;
		final int SNAKE = 1;
		final int ROAD = 0;
		int dir = 0; // 오른쪽
		int[] dy = { 0, 1, 0, -1 };
		int[] dx = { 1, 0, -1, 0 };
		Queue<int[]> queue = new LinkedList<int[]>();

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 사과는 4
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = APPLE;
		}

		int L = Integer.parseInt(br.readLine());
		Map<Integer, String> changeDir = new HashMap<Integer, String>();

		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			changeDir.put(Integer.parseInt(st.nextToken()), st.nextToken());
		}

		// 게임 시작~~
		int y = 0;
		int x = 0;
		// 뱀은 1
		map[y][x] = SNAKE;
		int[] idx = { 0, 0 };
		queue.offer(idx);

		Loop: while (true) {
			timer++;
			if (y + dy[dir] < 0 || x + dx[dir] < 0 || y + dy[dir] > N - 1 || x + dx[dir] > N - 1) {
				break Loop;
			}

			// 꼬리 부딪횜 확인
			Queue<int[]> temp = new LinkedList<int[]>();
			while (!queue.isEmpty()) {
				int[] tempIdx = queue.poll();
				if (y + dy[dir] == tempIdx[0] && x + dx[dir] == tempIdx[1]) {
					break Loop;
				}

				temp.offer(tempIdx);
			}
			queue = temp;

			// 마지막 꼬리 빼기
			// 맵에 있는 꼬리를 지우기
			if (map[y + dy[dir]][x + dx[dir]] == ROAD) {
				int[] tailIdx = queue.poll();
				map[tailIdx[0]][tailIdx[1]] = ROAD;
			}

			// 머리 그리기
			y += dy[dir];
			x += dx[dir];
			map[y][x] = SNAKE;
			int[] nowIdx = { y, x };
			queue.offer(nowIdx);

			if (changeDir.containsKey(timer)) {
				dir = changeDir.get(timer).equals("D") ? dir + 1 == 4 ? 0 : dir + 1 : dir - 1 == -1 ? 3 : dir - 1;
			}
		}

		System.out.println(timer);
	}
}