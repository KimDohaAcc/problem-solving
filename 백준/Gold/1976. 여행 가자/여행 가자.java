import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/*
	이는 어설프게 접근했다가는 헷갈릴 수 있는데 그래프를 직접 탐색하라는 문제가 아니다.
	그래프 정점간의 관계를 알아내야 한다.
	여러 그래프 알고리즘으로 풀 수 있겠지만
	가장 문제 의도와 가까운 Union-findset 서로소 집합을 사용하는게 가장 적절한 풀이이다.
	 */
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		parents = new int[N + 1];
		// 배열 초기화
		for (int i = 1; i <= N; i++) {
			makeset(i);
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					// 연결되어 있다면 유니온 한다
					union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		// 시작하는 노드
		int root = findset(Integer.parseInt(st.nextToken()));
		while (st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			// 만약 연결되어 있지 않다면??
			if (root != findset(num)) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	static void makeset(int x) {
		parents[x] = x;
	}

	static int findset(int x) {
		if (x == parents[x]) {
			return x;
		}

		return parents[x] = findset(parents[x]);
	}

	static void union(int x, int y) {
		if (x < y) {
			parents[findset(y)] = findset(x);

		} else {
			parents[findset(x)] = findset(y);
		}
	}
}