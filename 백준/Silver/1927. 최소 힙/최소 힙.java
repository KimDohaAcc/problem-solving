import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());

			if (x == 0) {
				if (queue.isEmpty()) {
					sb.append(0);
				} else {
					sb.append(queue.poll());
				}
				sb.append('\n');
			} else {
				queue.offer(x);
			}
		}

		System.out.println(sb);
	}
}