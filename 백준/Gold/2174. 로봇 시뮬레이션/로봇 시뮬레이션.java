import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static class Robot {

		int y;
		int x;
		int index;
		String dir;

		public Robot(int y, int x, int index, String dir) {
			this.y = y;
			this.x = x;
			this.index = index;
			this.dir = dir;
		}
	}

	static Map<String, int[]> map = new HashMap<>();
	static Robot[][] stage;
	static Map<String, String> mapR = new HashMap<>();
	static Map<String, String> mapL = new HashMap<>();
	static boolean flag;

	public static void main(String[] args) throws IOException {
		setMap();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		stage = new Robot[B + 1][A + 1];

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Robot> robotList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();

			Robot robot = new Robot(y, x, i + 1, dir);
			robotList.add(robot);
			stage[y][x] = robot;
		}

		Loop:
		for (int i = 0; i < M; i++) {
//			print();
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			Robot robot = robotList.get(idx - 1);
			String input = st.nextToken();

			switch (input) {
				case "F": {
					int[] move = map.get(robot.dir);
					int cnt = Integer.parseInt(st.nextToken());

					for (int j = 0; j < cnt; j++) {
//						print();

						robot.y += move[0];
						robot.x += move[1];

						if (robot.y < 1 || robot.y > B || robot.x < 1 || robot.x > A) {
							flag = true;
							sb.append("Robot ").append(idx).append(" crashes into the wall");
							break Loop;
						}

						if (stage[robot.y][robot.x] != null) {
							flag = true;
							Robot obstacle = stage[robot.y][robot.x];
							sb.append("Robot ").append(idx).append(" crashes into robot ").append(obstacle.index);
							break Loop;
						}

						stage[robot.y - move[0]][robot.x - move[1]] = null;
						stage[robot.y][robot.x] = robot;
					}
					break;
				}

				case "L": {
					int cnt = Integer.parseInt(st.nextToken()) % 4;
					for (int j = 0; j < cnt; j++) {
						robot.dir = mapL.get(robot.dir);
					}
					break;
				}

				case "R": {
					int cnt = Integer.parseInt(st.nextToken()) % 4;
					for (int j = 0; j < cnt; j++) {
						robot.dir = mapR.get(robot.dir);
					}
					break;

				}
			}
		}

		if (!flag) {
			sb.append("OK");
		}

		System.out.println(sb);
	}

	static void setMap() {
		map.put("E", new int[]{0, 1});
		map.put("W", new int[]{0, -1});
		map.put("N", new int[]{1, 0});
		map.put("S", new int[]{-1, 0});

		mapL.put("W", "S");
		mapL.put("E", "N");
		mapL.put("S", "E");
		mapL.put("N", "W");

		mapR.put("W", "N");
		mapR.put("E", "S");
		mapR.put("S", "W");
		mapR.put("N", "E");
	}

	static void print() {
		for (int i = stage.length - 1; i >= 1; i--) {
			for (int j = 1; j < stage[i].length; j++) {
				if (stage[i][j] == null) {
					System.out.print("x ");
				} else {
					System.out.print(stage[i][j].index + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}