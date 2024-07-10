import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Coin {
        int value;
        int count;

        Coin(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    static int answer = 0;
    static int money = 0;
    static Coin[] coins;
    static boolean[] dp;

    /*
    dp 배낭 문제
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < 3; t++) {
            int N = Integer.parseInt(br.readLine());
            answer = 0;
            money = 0;
            coins = new Coin[N];
            dp = new boolean[50001]; // 최대 금액이 10만이다 절반만 보면 된다
            dp[0] = true;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());

                // 총합을 구한다
                money += value * count;
                Coin coin = new Coin(value, count);
                coins[i] = coin;

                // 동전 가치의 개수 배수를 다 true로 준다
                for (int j = 1; j <= count; j++) {
                    if (value * j > 50000) {
                        break;
                    }
                    dp[value * j] = true;
                }
            }

            if (money % 2 == 0) { // 최종 금액이 2의 배수가 아니면 반으로 나눌 수 없음
                if (dp[money / 2]) {
                    answer = 1;
                } else {
                    dp(N, money);
                }
            }

            sb.append(answer).append('\n');
        }
        System.out.println(sb);
    }

    static void dp(int n, int money) {
        for (int i = 0; i < n; i++) {
            int value = coins[i].value;
            int count = coins[i].count;

            for (int j = money / 2 - value; j > 0; j--) {
                if (dp[j]) {
                    for (int k = 1; k <= count && j + value * k <= money / 2; k++) {
                        dp[j + value * k] = true;

                        if (j + value * k == money / 2) {
                            answer = 1;
                        }
                    }
                }
            }
        }
    }
}