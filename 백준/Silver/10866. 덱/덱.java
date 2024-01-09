import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		Deque<Integer> deque = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			String input = scan.next();
			switch (input) {
				case "push_front":
					deque.addFirst(scan.nextInt());
					break;
				case "push_back":
					deque.addLast(scan.nextInt());
					break;
				case "pop_front":
					sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append('\n');
					break;
				case "pop_back":
					sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append('\n');
					break;
				case "size":
					sb.append(deque.size()).append('\n');
					break;
				case "empty":
					sb.append(deque.isEmpty() ? 1 : 0).append('\n');
					break;
				case "front":
					sb.append(deque.isEmpty() ? -1 : deque.getFirst()).append('\n');
					break;
				case "back":
					sb.append(deque.isEmpty() ? -1 : deque.getLast()).append('\n');
					break;
			}
		}

		System.out.println(sb);
	}
}