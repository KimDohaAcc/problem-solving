import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		// 시작 인덱스가 1
		int[] cake = new int[L + 1];
		int preMax = 0;
		int preMaxIdx = 0;
		int max = 0;
		int maxIdx = 0;

		// 방청객의 번호
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			if (preMax < k - p) {
				preMax = k - p;
				preMaxIdx = i;
			}

			int[] temp = Arrays.copyOf(cake, L + 1);
			int cnt = 0;
			for (int j = p; j <= k; j++) {
				if (cake[j] == 0) {
					temp[j] = i;
					cnt++;
				}
			}

			if (cnt > 0) {
				cake = temp;
			}

			if (cnt > max) {
				max = cnt;
				maxIdx = i;
			}
		}

		System.out.println(preMaxIdx);
		System.out.println(maxIdx);
		// 가장 많은 조각을 받을 것으로 기대한 방청객
		// 실제로 가장 많이 받은 사람
		// 2명 이상이라면, 번호가 작은 사람!
	}
}