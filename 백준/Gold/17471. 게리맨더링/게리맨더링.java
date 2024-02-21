import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {

	static int[] persons;
	static int N;
	static ArrayList<Integer>[] list;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		list = new ArrayList[N + 1];

		persons = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int all = Arrays.stream(persons).sum();

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int x = Integer.parseInt(st.nextToken());
				list[i].add(x);
				list[x].add(i);
			}
		}

		ArrayList<Integer> addList = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			addList.add(i);
		}

		dfs(1, new boolean[N + 1], new ArrayList<>(), addList);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void dfs(int idx, boolean[] visited, ArrayList<Integer> numList, ArrayList<Integer> otherList) {
		if (idx == N + 1) {
			return;
		}

		if (!numList.isEmpty() && !otherList.isEmpty()) {
			if (bfs(numList) && bfs(otherList)) {
				int sum = numList.stream().map(num -> persons[num - 1]).reduce(0, Integer::sum);
				int otherSum = otherList.stream().map(num -> persons[num - 1]).reduce(0, Integer::sum);
				min = Math.min(min, Math.abs(sum - otherSum));
			}
		}

		for (int i = idx; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				numList.add(i);
				otherList.remove(Integer.valueOf(i));

				dfs(i + 1, visited, numList, otherList);
				visited[i] = false;
				numList.remove(Integer.valueOf(i));
				otherList.add(i);
			}
		}
	}

	static boolean bfs(ArrayList<Integer> numList) {
		Queue<Integer> queue = new ArrayDeque<>();
		ArrayList<Integer> copyList = new ArrayList<>(numList);
		queue.offer(copyList.remove(0));

		boolean[] visited = new boolean[N + 1];
		visited[queue.peek()] = true;

		while (!queue.isEmpty()) {
			int x = queue.poll();

			for (int i = 0; i < list[x].size(); i++) {
				int y = list[x].get(i);
				if (!visited[y] && copyList.contains(y)) {
					visited[y] = true;
					copyList.remove(Integer.valueOf(y));
					queue.offer(y);
				}
			}
		}

		if (copyList.isEmpty()) {
			return true;
		}

		return false;
	}
}