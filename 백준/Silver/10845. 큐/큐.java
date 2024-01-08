import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		Queue<Integer> que = new LinkedList<>();
		int back = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String str = scan.next();

			switch (str) {
				case "push":
					int n = scan.nextInt();
					que.offer(n);
					back = n;
					break;

				case "pop":
					sb.append(que.isEmpty() ? -1 : que.poll()).append('\n');
					break;

				case "size":
					sb.append(que.size()).append('\n');
					break;

				case "empty":
					sb.append(que.isEmpty() ? 1 : 0).append('\n');
					break;

				case "front":
					sb.append(que.isEmpty() ? -1 : que.peek()).append('\n');
					break;

				case "back":
					sb.append(que.isEmpty() ? -1 : back).append('\n');
					break;
			}
		}
		System.out.println(sb);
	}
}