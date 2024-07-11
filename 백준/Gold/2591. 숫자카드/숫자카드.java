import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 숫자는 1~34
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        int[] nums = new int[num.length() + 1];
        int[] dp = new int[num.length() + 1];

        for (int i = 1; i < nums.length; i++) {
            nums[i] = num.charAt(i - 1) - '0';
        }

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < nums.length; i++) {
            int n = nums[i - 1] * 10 + nums[i];
            if (nums[i] == 0 || nums[i - 1] == 0 || i + 1 < nums.length && (nums[i + 1] == 0) || n > 34) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        System.out.println(dp[num.length()]);
    }
}