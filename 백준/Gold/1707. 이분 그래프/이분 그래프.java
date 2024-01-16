import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 테스트의 개수
		int K = scan.nextInt();
		Loop:
		for (int i = 0; i < K; i++) {
			// 정점의 개수
			int V = scan.nextInt();
			// 간선의 개수
			int E = scan.nextInt();

			List<Integer>[] list = new ArrayList[V + 1];

			for (int j = 1; j <= V; j++) {
				list[j] = new ArrayList<>();
			}

			for (int j = 0; j < E; j++) {
				int num = scan.nextInt();
				int link = scan.nextInt();

				list[num].add(link);
				list[link].add(num);
			}

			int[] visited = new int[V + 1];
			Queue<Integer> que = new ArrayDeque<>();

			for (int j = 1; j <= V; j++) {
				if (visited[j] == 0) {
					que.offer(j);
					boolean res = bfs(que, list, visited);

					if (!res) {
						System.out.println("NO");
						continue Loop;
					}
				}
			}

			System.out.println("YES");
		}
	}

	static boolean bfs(Queue<Integer> que, List<Integer>[] list, int[] visited) {
		while (!que.isEmpty()) {
			int n = que.poll();

			for (int j = 0; j < list[n].size(); j++) {
				int node = list[n].get(j);
				if (visited[node] == 0) {
					visited[node] = visited[n] == 1 ? 2 : 1;
					que.offer(node);
				}

				if (visited[node] == visited[n]) {
					return false;
				}
			}
		}

		return true;
	}
}