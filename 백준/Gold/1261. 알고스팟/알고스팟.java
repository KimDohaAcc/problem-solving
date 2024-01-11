import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	static class Room implements Comparable<Room> {

		int y;
		int x;
		int isWall;
		int cnt;
		int turn;

		public Room(int y, int x, int isWall, int cnt, int turn) {
			this.y = y;
			this.x = x;
			this.isWall = isWall;
			this.cnt = cnt;
			this.turn = turn;
		}

		@Override
		public int compareTo(Room o) {
			return this.cnt == o.cnt ? this.isWall - o.isWall : this.cnt - o.cnt;
		}
	}

	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int N;
	static int M;
	static int[][] map;
	static int minCnt = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 가로
		M = Integer.parseInt(st.nextToken());
		// 세로
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		PriorityQueue<Room> pq = new PriorityQueue<>();
		pq.offer(new Room(0, 0, 0, 0, 1));

		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;

		escape(pq, visited);
		System.out.println(minCnt);
	}

	static void escape(PriorityQueue<Room> pq, boolean[][] visited) {
		while (!pq.isEmpty()) {
			Room room = pq.poll();

			if (room.cnt >= minCnt) {
				continue;
			}

			if (room.y == N - 1 && room.x == M - 1) {
				minCnt = room.cnt;
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int ny = room.y + dy[i];
				int nx = room.x + dx[i];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
					continue;
				}

				if (visited[ny][nx]) {
					continue;
				}

				visited[ny][nx] = true;
				pq.offer(new Room(ny, nx, map[ny][nx], map[ny][nx] == 1 ? room.cnt + 1 : room.cnt, room.turn + 1));
			}
		}
	}
}