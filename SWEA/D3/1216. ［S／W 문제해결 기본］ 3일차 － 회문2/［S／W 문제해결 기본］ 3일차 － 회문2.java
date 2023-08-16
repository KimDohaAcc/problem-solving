import java.util.Scanner;

public class Solution {
	public static String[][] arr = new String[100][100];
	public static int max = 0;
	public static int cntB = 0;
	public static int cntBB = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 테스트 케이스 돌기
		for (int i = 0; i < 10; i++) {
			max = 0;
			System.out.print("#" + scan.nextInt() + " ");
			scan.nextLine();

			// 입력
			for (int j = 0; j < 100; j++) {
				arr[j] = scan.nextLine().split("");
			}

			// 가로 세로 탐색
			banbokmoon("garo");
			banbokmoon("sero");
			System.out.println(max);
		}
	}

	public static void init() {
		cntB = 0;
		cntBB = 0;
	}

	public static void banbokmoon(String word) {
		for (int j = 0; j < 100; j++) {
			// cnt 초기화
			init();

			for (int h = 0; h < 100; h++) {
				// 찾을 인덱스(짝수 길이)
				int findIdx = h - 1 - cntB;

				cntB = word.equals("garo") ? maxStr(j, findIdx, cntB, arr[j][h], 0)
						: maxStr(findIdx, j, cntB, arr[h][j], 0);

				// 찾을 인덱스(홀수 길이)
				findIdx = h - 2 - cntBB;

				cntBB = word.equals("garo") ? maxStr(j, findIdx, cntBB, arr[j][h], 1)
						: maxStr(findIdx, j, cntBB, arr[h][j], 1);
			}
		}
	}

	public static int maxStr(int y, int x, int cnt, String str, int odd) {
		if (y < 0 || x < 0) {
			return cnt;
		}

		if (arr[y][x].equals(str)) {
			cnt += 2;
		}

		else {
			cnt = 0;
		}

		// 홀수면 cnt에 1 더해주기
		max = Math.max(cnt + odd, max);
		return cnt;
	}
}