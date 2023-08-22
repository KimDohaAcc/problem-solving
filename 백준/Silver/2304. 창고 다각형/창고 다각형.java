import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		/*
		 * x 순서대로 오름차순으로 정렬. map 이용하기
		 * 앞에서부터 max를 활용해서 더하기. 갱신해가면서 더이상 높은 게 없으면
		 * 그때부터 다음 max만큼 내려와도 됨! 가장 높은 것 기준으로 max 배열을 만들어서 칸을 채워주자~
		 */

		int maxHigh = 0;
		int maxHighIdx = 0;
		int maxWidth = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int k = 0; k < N; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			map.put(L, H);

			if (maxHigh < H) {
				maxHigh = H;
				maxHighIdx = L;
			}

			maxWidth = Math.max(maxWidth, L);
		}

		// 앞에 채우기
		int[] container = new int[maxWidth + 1];
		int height = 0;
		for (int i = 0; i < maxHighIdx; i++) {
			if (map.containsKey(i)) {
				height = Math.max(height, map.get(i));
			}

			container[i] = height;
		}

		// 제일 큰 거
		container[maxHighIdx] = maxHigh;

		// 뒤에서 채우기
		height = 0;
		for (int i = container.length - 1; i > maxHighIdx; i--) {
			if (map.containsKey(i)) {
				height = Math.max(height, map.get(i));
			}

			container[i] = height;
		}

		// 값 더하기
		int sum = 0;
		for (int h : container) {
			sum += h;
		}

		System.out.println(sum);

	}
}