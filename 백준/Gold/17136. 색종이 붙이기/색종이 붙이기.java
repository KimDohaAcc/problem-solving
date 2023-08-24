import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[][] paper = new int[10][10];

		// 입력 받기
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				paper[i][j] = scan.nextInt();
			}
		}

		// 각 종류별로 색종이 장수를 저장해주기
		// 파라미터로 넘겨줄거야! 각 루트에서 장수가 부족한지 확인하기 위해
		int[] paperKind = new int[] { 0, 5, 5, 5, 5, 5 };
		// 각 루트에서 paper에 조작을 하기 위해(1->0) 파라미터로 넘기자
		// 좌표와 붙인 색종이 개수를 세는 변수도!
		glue(paper, paperKind, 0, 0, 0);

		// min에 저장된 게 없다면 -1 출력
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void glue(int[][] paper, int[] paperKind, int y, int x, int gaesu) {
		// 잘 붙이면서 끝까지 갔으면! 최솟값 업데이트
		if (y == 9 && x == 10) {
			// 둘 중 더 작은 값을 저장하기
			min = Math.min(gaesu, min);
			return;
		}

		// 현재 최솟값을 넘어가면 더 탐색하는 의미 없어~
		if (min <= gaesu) {
			return;
		}

		// 한 줄 바꾸기
		else if (x == 10) {
			y += 1;
			x = 0;
		}

		// 1일 때
		if (paper[y][x] == 1) {
			// 뒤에서부터 찾아야 최솟값 찾기 용이
			for (int k = 5; k >= 1; k--) {
				// 붙일 장수가 없다면 건너뛰기
				if (paperKind[k] == 0) {
					continue;
				}

				int cnt = 0;
				// paper에 마킹을 하고 다음으로 넘겨줄 temp 배열
				// 배열 복사
				int[][] temp = new int[10][10];
				for (int i = 0; i < paper.length; i++) {
					temp[i] = Arrays.copyOf(paper[i], paper[i].length);
				}

				// 색종이 1~5까지 전부 탐색하기
				Loop: for (int h = 0; h < k; h++) {
					for (int g = 0; g < k; g++) {
						// 범위 안에 있고 자리가 1이라면
						if (y + h < 10 && x + g < 10 && paper[y + h][x + g] == 1) {
							cnt++;
							temp[y + h][x + g] = 0;
						}

						// 붙일 수 없는 자리가 있다면 즉시 종료 후 다음 크기 탐색
						else {
							break Loop;
						}

						// 색종이의 넓이만큼 1을 찾았다면
						if (cnt == k * k) {
							// 사용 표시
							paperKind[k]--;
							glue(temp, paperKind, y, x + 1, gaesu + 1);
							// 되돌리기
							paperKind[k]++;
						}
					}
				}
			}
		}

		// 1이 아니라면 옆으로 넘기기
		else {
			glue(paper, paperKind, y, x + 1, gaesu);
		}
	}
}