import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();

		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String input = scan.next();

//			switch (input) {
//				case "push" : stack.push(scan.nextInt());
//				case "top" : sb.append(stack.isEmpty() ? "-1" : stack.peek());
//				case "size" : sb.append(stack.size());
//				case "empty" : sb.append(stack.isEmpty() ? "1" : "0");
//				case "pop" : sb.append(stack.isEmpty() ? "-1" : stack.pop());
//			}

			switch (input) {
				case "push":
					stack.push(scan.nextInt());
					break;
				case "top":
					sb.append(stack.isEmpty() ? "-1" : stack.peek()).append('\n');
					break;
				case "size":
					sb.append(stack.size()).append('\n');
					break;
				case "empty":
					sb.append(stack.isEmpty() ? "1" : "0").append('\n');
					break;
				case "pop":
					sb.append(stack.isEmpty() ? "-1" : stack.pop()).append('\n');
					break;
			}
		}

		System.out.println(sb);
	}
}