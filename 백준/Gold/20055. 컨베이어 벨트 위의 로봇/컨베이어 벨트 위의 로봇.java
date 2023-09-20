import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] durability = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 0은 내구도
        // 1은 로봇
        ArrayList<int[]> list = new ArrayList<>();
        final int ROBOT = -1;
        final int EMPTY = -2;

        // 1~2N까지 들어감
        for (int i = 0; i < 2 * N; i++) {
            list.add(new int[]{durability[i], EMPTY});
        }

        int zeroCnt = 0;
        int turn = 0;
        // 가동 시작
        while (zeroCnt < K) {
            // 1칸 회전
            list.add(0, list.remove(2 * N - 1));

            // 로봇 이동
            // 뒤에서부터
            for (int i = 2 * N - 1; i >= 0; i--) {
                int next = i == 2 * N - 1 || i == 0 ? 0 : i + 1;

                // N번째가 로봇이면 상시 내림
                if (list.get(N - 1)[1] == ROBOT) {
                    list.get(N - 1)[1] = EMPTY;
                }

                // 첫번째는 고냥 올리기
                if (i == 0 && list.get(next)[0] > 0) {
                    list.get(next)[1] = ROBOT;
                    list.get(next)[0]--;
                }

                // 다음 칸 비어 있고 내가 로봇이라면
                if (list.get(i)[1] == ROBOT && list.get(next)[1] == EMPTY && list.get(next)[0] > 0) {
                    // 앞으로 한 칸 이동
                    list.get(i)[1] = EMPTY;
                    list.get(next)[1] = ROBOT;
                    list.get(next)[0]--;
                }

                // 내구도가 0이라면
                // 카운트 하고 마킹해주기
                if (list.get(next)[0] == 0) {
                    zeroCnt++;
                    list.get(next)[0] = Integer.MIN_VALUE;
                }
            }


            turn++;
        }

        System.out.println(turn);
    }
}