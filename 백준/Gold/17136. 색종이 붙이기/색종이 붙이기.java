import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[][] paper = new int[10][10];
		int paperCnt = 0;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				paper[i][j] = scan.nextInt();
				if (paper[i][j] == 1) {
					paperCnt++;
				}
			}
		}

		int[] paperKind = new int[] { 0, 5, 5, 5, 5, 5 };
		glue(paper, paperKind, 0, 0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void glue(int[][] paper, int[] paperKind, int y, int x, int gaesu) {
		if (y == 9 && x == 10) {
			min = Math.min(gaesu, min);
			return;
		}

		else if (x == 10) {
			y += 1;
			x = 0;
		}

		if (paper[y][x] == 1) {
			for (int k = 5; k >= 1; k--) {
				int cnt = 0;
				int[][] temp = new int[10][10];
				for (int i = 0; i < paper.length; i++) {
					temp[i] = Arrays.copyOf(paper[i], paper[i].length);
				}

				Loop: for (int h = 0; h < k; h++) {
					for (int g = 0; g < k; g++) {
						if (y + h < 10 && x + g < 10 && paper[y + h][x + g] == 1) {
							cnt++;
							temp[y + h][x + g] = 0;
						}

						else {
							break Loop;
						}

						if (cnt == k * k && paperKind[k] > 0) {
							paperKind[k]--;
							glue(temp, paperKind, y, x + 1, gaesu + 1);
							paperKind[k]++;
						}
					}
				}
			}
		}

		else {
			glue(paper, paperKind, y, x + 1, gaesu);
		}
	}
}