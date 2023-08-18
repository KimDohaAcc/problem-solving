import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int max;
	static int[] taste;
	static int[] kcal;
	static int limitKcal;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			System.out.print("#" + (tc + 1) + " ");
			// 입력
			taste = new int[Integer.parseInt(st.nextToken())];
			kcal = new int[taste.length];
			limitKcal = Integer.parseInt(st.nextToken());
			max = 0;

			for (int i = 0; i < taste.length; i++) {
				st = new StringTokenizer(br.readLine());
				taste[i] = Integer.parseInt(st.nextToken());
				kcal[i] = Integer.parseInt(st.nextToken());
			}

			tasteCount(0, 0, 0);
			System.out.println(max);

			// 제한 칼로리 이하의 조합 중 가장 점수가 높은 햄버거의 점수 출력
		}
	}

	public static void tasteCount(int x, int nowKcal, int tasteScore) {
		if (nowKcal <= limitKcal) {
			max = Math.max(max, tasteScore);
		}

		else
			return;

		if (x == taste.length) {
			return;
		}

		tasteCount(x + 1, nowKcal + kcal[x], tasteScore + taste[x]);
		tasteCount(x + 1, nowKcal, tasteScore);
	}
}