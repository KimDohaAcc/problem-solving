import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int[][] arr = new int[n][2];
		for (int i = 0; i < n; i++) {
			arr[i][0] = scan.nextInt();
			arr[i][1] = scan.nextInt();
		}

		int cnt = 0;
		List<Integer> list = new ArrayList<>();

		if (arr[0][1] > 0) {
			list.add(arr[0][1]);
			cnt++;
		}

		for (int i = 1; i < n; i++) {
			// 내가 더 크네
			if (arr[i][1] > arr[i - 1][1]) {
				list.add(arr[i][1]);
				cnt++;
			}

			// 내가 더 작네
			else if (arr[i][1] < arr[i - 1][1]) {
				boolean check = false;
				for (int j = list.size() - 1; j >= 0; j--) {
					if (arr[i][1] == list.get(j)) {
						check = true;
					} else if (arr[i][1] < list.get(j)) {
						list.remove(j);
					}
				}

				// 같은 높이 애가 없다
				if (!check && arr[i][1] > 0) {
					list.add(arr[i][1]);
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}
}