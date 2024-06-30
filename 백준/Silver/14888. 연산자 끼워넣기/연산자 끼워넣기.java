import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
    연산자를 줄 세우는 문제
     */
    static int[] math;
    static int[] nums;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        math = new int[N - 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                math[idx++] = i;
            }
        }

        dfs(new boolean[math.length], nums[0], 0);
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(boolean[] visited, int num, int count) {
        if (count == math.length) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < math.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int temp = num;
                switch (math[i]) {
                    case 0 -> temp += nums[count + 1];
                    case 1 -> temp -= nums[count + 1];
                    case 2 -> temp *= nums[count + 1];
                    case 3 -> temp /= nums[count + 1];
                }

                dfs(visited, temp, count + 1);
                visited[i] = false;
            }
        }
    }
}