import java.util.Scanner;

public class Main {
	public static int max, N, M;
	public static boolean[] visited;
	public static int[] cards;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();
		M = scan.nextInt();
		cards = new int[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			cards[i] = scan.nextInt();
		}
		sum(0, 0);

		System.out.println(max);
	}

	public static void sum(int now, int cnt) {
		if (now > M) {
			return;
		}

		else if (cnt == 3) {
			max = Integer.max(max, now);
		}

		else if (cnt < 3) {
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					visited[i] = true;
					sum(now + cards[i], cnt + 1);
					visited[i] = false;
				}
			}
		}
	}
}