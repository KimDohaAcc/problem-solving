import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			System.out.print("#" + scan.nextInt() + " ");
			String find = scan.next();
			String str = scan.next();
			String empty = "";

			int cnt = 0;
			while (str.contains(find)) {
				str = str.replaceFirst(find, empty);
				cnt++;
			}
			System.out.println(cnt);
		}
	}
}