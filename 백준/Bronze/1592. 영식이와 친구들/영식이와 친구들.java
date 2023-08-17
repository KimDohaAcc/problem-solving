import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int N = scan.nextInt();
		int M = scan.nextInt();
		int L = scan.nextInt();

		// 공 받은 횟수 홀수 : 시계방향 L번째
		// 공 받은 횟수 짝수 : 반시계방향 L번째
		// 한 사람이 공을 M번 받으면 게임 종료!

		int[] cnt = new int[N + 1];
		int now = 1;
		int bounce = 0;

		while (true) {
			// 공 던지기
			if (cnt[now] % 2 == 1) {
				now = now + L > N ? now + L - N : now + L;
			}

			else {
				now = now - L < 1 ? now - L + N : now - L;
			}

			if (++cnt[now] == M) {
				break;
			}

			bounce++;
		}

		System.out.println(bounce);
	}
}