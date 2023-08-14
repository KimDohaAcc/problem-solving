import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[][] arr = null;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int num = Integer.parseInt(st.nextToken());
			yeonsan(num);
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void yeonsan(int num) {
		if (num == 1)
			topToBottom();
		else if (num == 2)
			leftToRight();
		else if (num == 3)
			turnRight();
		else if (num == 4)
			turnLeft();
		else if (num == 5)
			moveRight();
		else if (num == 6)
			moveLeft();
	}

	public static void topToBottom() {
		int[][] temp = new int[arr.length][arr[0].length];

		for (int i = temp.length - 1; i >= 0; i--) {
			for (int j = 0; j < temp[0].length; j++) {
				temp[i][j] = arr[(temp.length - 1) - i][j];
			}
		}

		arr = temp;
	}

	public static void leftToRight() {
		int[][] temp = new int[arr.length][arr[0].length];

		for (int j = temp[0].length - 1; j >= 0; j--) {
			for (int i = 0; i < temp.length; i++) {
				temp[i][j] = arr[i][temp[0].length - 1 - j];
			}
		}

		arr = temp;
	}

	public static void turnRight() {
		int[][] temp = new int[arr[0].length][arr.length];

		int n = 0;
		int m = 0;
		for (int j = temp[0].length - 1; j >= 0; j--) {
			m = 0;
			for (int i = 0; i < temp.length; i++) {
				temp[i][j] = arr[n][m++];
			}
			n++;
		}
		arr = temp;
	}

	public static void turnLeft() {
		int[][] temp = new int[arr[0].length][arr.length];

		int n = 0;
		int m = 0;
		for (int j = 0; j < temp[0].length; j++) {
			m = 0;
			for (int i = temp.length - 1; i >= 0; i--) {
				temp[i][j] = arr[n][m++];
			}
			n++;
		}
		arr = temp;
	}

	public static void moveRight() {
		int[][] temp = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (i < arr.length / 2 && j < arr[0].length / 2) {
					temp[i][arr[0].length / 2 + j] = arr[i][j];
				}

				if (i < arr.length / 2 && j >= arr[0].length / 2) {
					temp[arr.length / 2 + i][j] = arr[i][j];
				}

				if (i >= arr.length / 2 && j >= arr[0].length / 2) {
					temp[i][j - arr[0].length / 2] = arr[i][j];
				}

				if (i >= arr.length / 2 && j < arr[0].length / 2) {
					temp[i - arr.length / 2][j] = arr[i][j];
				}
			}
		}
		arr = temp;
	}

	public static void moveLeft() {
		int[][] temp = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (i < arr.length / 2 && j < arr[0].length / 2) {
					temp[arr.length / 2 + i][j] = arr[i][j];
				}

				if (i < arr.length / 2 && j >= arr[0].length / 2) {
					temp[i][j - arr[0].length / 2] = arr[i][j];
				}

				if (i >= arr.length / 2 && j >= arr[0].length / 2) {
					temp[i - arr.length / 2][j] = arr[i][j];
				}

				if (i >= arr.length / 2 && j < arr[0].length / 2) {
					temp[i][arr[0].length / 2 + j] = arr[i][j];
				}
			}
		}
		arr = temp;
	}
}