import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();
        /*
        dp 기반 LCS
         */
        int dp[] = new int[b.length + 1];
        int max = 0;
        for (int i = 1; i <= a.length; i++) {
            int prev = 0; // 직전까지 비교한 결과를 저장
            for (int j = 1; j <= b.length; j++) {
                int temp = dp[j];
                if (a[i - 1] == b[j - 1]) {
                    dp[j] = prev + 1;
                    max = Math.max(dp[j], max);
                } else {
                    dp[j] = 0;
                }

                prev = temp;
            }
        }
        System.out.println(max);
    }
}