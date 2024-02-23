import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			// 순열의 크기
			int N = Integer.parseInt(br.readLine());
			int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			p = new int[N + 1];

			for (int j = 0; j < N; j++) {
				makeset(arr[j]);
			}

			for (int j = 1; j <= N; j++) {
				union(j, arr[j - 1]);
			}

			sb.append(countCycle(N)).append('\n');
		}

		System.out.println(sb);
	}

	static void makeset(int x) {
		p[x] = x;
	}

	static int findset(int x) {
		if (p[x] == x) {
			return p[x];
		}

		return p[x] = findset(p[x]);
	}

	static void union(int x, int y) {
		p[findset(y)] = findset(x);
	}

	static int countCycle(int n) {
		List<Integer> list = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			int boss = findset(i);
			if (!list.contains(boss)) {
				list.add(boss);
			}
		}

		return list.size();
	}
}