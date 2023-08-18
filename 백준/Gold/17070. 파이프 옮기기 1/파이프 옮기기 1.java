import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] pipe;
	static int N;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pipe = new int[N][N];
		// 1,1 1,2는 항상 빈 칸
		// 모든 경우의 수 구하기
		// 빈칸 0 벽 1
		// 가로 : 2
		// 세로 : 3
		// 대각선 : 4
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				pipe[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		pipe[0][0] = 2;
		pipe[0][1] = 2;
		move(0, 1);
		System.out.println(cnt);
	}

	static void move(int y, int x) {
		if (y == N - 1 && x == N - 1) {
			cnt++;
			return;
		}

		switch (pipe[y][x]) {
		case 2:
			chooseH(y, x);
			break;

		case 3:
			chooseV(y, x);
			break;

		case 4:
			chooseD(y, x);
			break;

		}
	}

	static void moveH(int y, int x) {
		pipe[y][x] = 2;
		move(y, x);
		pipe[y][x] = 0;
	}

	static void moveV(int y, int x) {
		pipe[y][x] = 3;
		move(y, x);
		pipe[y][x] = 0;
	}

	static void moveD(int y, int x) {
		pipe[y][x] = 4;
		move(y, x);
		pipe[y][x] = 0;
	}

	static void chooseH(int y, int x) {
		if (x + 1 < N && pipe[y][x + 1] == 0) {
			moveH(y, x + 1);
		}

		if (y + 1 < N && x + 1 < N) {
			if (pipe[y + 1][x + 1] == 0 && pipe[y][x + 1] == 0 && pipe[y + 1][x] == 0)
				moveD(y + 1, x + 1);
		}
	}

	static void chooseV(int y, int x) {
		if (y + 1 < N && pipe[y + 1][x] == 0) {
			moveV(y + 1, x);
		}

		if (y + 1 < N && x + 1 < N) {
			if (pipe[y + 1][x + 1] == 0 && pipe[y][x + 1] == 0 && pipe[y + 1][x] == 0)
				moveD(y + 1, x + 1);
		}
	}

	static void chooseD(int y, int x) {
		if (x + 1 < N && pipe[y][x + 1] == 0) {
			moveH(y, x + 1);
		}

		if (y + 1 < N && pipe[y + 1][x] == 0) {
			moveV(y + 1, x);
		}

		if (y + 1 < N && x + 1 < N) {
			if (pipe[y + 1][x + 1] == 0 && pipe[y][x + 1] == 0 && pipe[y + 1][x] == 0)
				moveD(y + 1, x + 1);
		}
	}
}