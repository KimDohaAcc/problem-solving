import java.util.Scanner;

/*
 * 구매 개수는 제한이 없지만 하루에 1개만 살 수 있다!
 * 가장 큰 이익을 만드는 것이 목적
 * 가장 비싼 날을 저장하는 배열을 만들기!
 * 
 * 맨 뒤에서부터 비싼 날을 저장하는 배열을 완성하기
 * 반복문을 돌면서 오늘 값과 비싼날의 값을 비교해 크면 팔기!
 * 
 */
public class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();

		for (int tc = 0; tc < T; tc++) {
			System.out.print("#" + (tc + 1) + " ");
			int N = scan.nextInt();

			int[] arr = new int[N];
			// 입력
			for (int i = 0; i < N; i++) {
				arr[i] = scan.nextInt();
			}
			int[] max = new int[N];
			int maxValue = 0;
			for (int i = N - 1; i >= 0; i--) {
				maxValue = Math.max(maxValue, arr[i]);
				max[i] = maxValue;
			}

			Long total = 0L;
			// 마지막 날은 살 필요 없어~
			for (int i = 0; i < N - 1; i++) {
				// 가장 비싼 날과 오늘의 차를 구해서
				if (max[i] - arr[i] > 0) {
					// 팔기
					total += max[i] - arr[i];
				}
			}

			System.out.println(total);
		}
	}
}