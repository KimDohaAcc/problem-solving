import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	투 포인터로 간단하게 해결을 할 수 있다
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

//		System.out.println(Arrays.toString(arr));
		Arrays.sort(arr);

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			// 포인터를 2개 주기
			int left = 0;
			int right = N - 1;

			int num = arr[i];

			// 두 포인터가 만날 때까지
			while (left < right) {
				int sum = arr[left] + arr[right];
				if (sum == num) {
					// 포인터가 나를 찍었다면
					if (left == i) {
						// 왼쪽 넘기기
						left++;
					} else if (right == i) {
						// 오른쪽 넘기기
						right--;
					} else {
						cnt++;
						break;
					}
				} else if (sum < num) {
					left++;
				} else {
					right--;
				}
			}
		}
		System.out.println(cnt);
	}
}