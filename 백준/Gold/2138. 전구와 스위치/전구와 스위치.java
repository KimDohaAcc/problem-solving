import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

	static int N;
	static int[] answer;

	static int minCnt = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int[] bulb = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		answer = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

		// 첫 번째 스위치를 누르는 경우와 누르지 않는 경우로 나눈다
		int[] firstClickBulb = Arrays.copyOf(bulb, bulb.length);
		firstClickBulb[0] ^= 1;
		firstClickBulb[1] ^= 1;

		clickClick(bulb, 0);
		clickClick(firstClickBulb, 1);

//		System.out.println(Arrays.toString(bulb));
//		System.out.println(Arrays.toString(firstClickBulb));
		System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
	}

	static void clickClick(int[] now, int cnt) {
		for (int i = 1; i < now.length; i++) {
			if (now[i - 1] != answer[i - 1]) {
				cnt++;
				now[i - 1] = now[i - 1] ^ 1;
				now[i] = now[i] ^ 1;

				if (i + 1 < now.length) {
					now[i + 1] ^= 1;
				}
			}
		}

		if (correctCheck(now)) {
			minCnt = Math.min(cnt, minCnt);
		}
	}

	static boolean correctCheck(int[] now) {
		for (int i = 0; i < N; i++) {
			if (now[i] != answer[i]) {
				return false;
			}
		}

		return true;
	}
}