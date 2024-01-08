import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		// 자르면 자른 순간  stack에 남아 있는 친구들만큼 쇠막대기가 생김
		// 쇠막대기가 닫힐 때 하나가 추가로 생긴다

		Scanner scan = new Scanner(System.in);
		String str = scan.next();

		Stack<Character> stack = new Stack<>();

		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '(') {
				// 레이저다
				if (i < str.length() - 1 && str.charAt(i + 1) == ')') {
					cnt += stack.size();
					i++;
				}

				// 레이저 아니면 막대 시작
				else {
					stack.push('(');
				}
			} else {
				// 막대 끝
				// 조각 개수를 1 늘려줘야 함
				cnt++;
				stack.pop();
			}
		}

		System.out.println(cnt);
	}
}