import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int[] time = new int[N];
		for (int i = 0; i < N; i++) {
			time[i] = scan.nextInt();
		}

		Arrays.sort(time);
		int sum = time[0];
		for (int i = 1; i < time.length; i++) {
			time[i] += time[i - 1];
			sum += time[i];
		}

		System.out.println(sum);
	}
}