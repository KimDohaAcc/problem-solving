import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 세로
        int N = Integer.parseInt(st.nextToken());
        // 가로
        int M = Integer.parseInt(st.nextToken());
        // 주사위 좌표
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        // 명령의 개수
        int K = Integer.parseInt(st.nextToken());

        // 지도
        int[][] diceMap = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                diceMap[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dy = {0, 0, 0, -1, 1};
        int[] dx = {0, 1, -1, 0, 0};

        // 1부터 시작
        int nowNum = 1;
        int bottom = 7 - nowNum;
        int left = 4;
        int right = 7 - left;
        int up = 2;
        int down = 7 - up;
        int[] diceNumber = new int[7];

        // 이동 명령
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int k = Integer.parseInt(st.nextToken());
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                continue;
            }

            int temp = 0;

            if (k == 1) {
                // 왼쪽 게 올라옴
                temp = left;
                right = nowNum;
                left = 7 - nowNum;
            } else if (k == 2) {
                // 오른쪽 게 올라옴
                temp = right;
                left = nowNum;
                right = 7 - nowNum;
            } else if (k == 3) {
                // 아래쪽 게 올라옴
                temp = down;
                up = nowNum;
                down = 7 - nowNum;
            } else if (k == 4) {
                // 위쪽 게 올라옴
                temp = up;
                down = nowNum;
                up = 7 - nowNum;
            }

            nowNum = temp;
            bottom = 7 - temp;

            // 현재 숫자 갱신
            // 주사위가 0이면 가져오기, 아니면 맵에 쓰기
            if (diceMap[ny][nx] == 0) {
                diceMap[ny][nx] = diceNumber[bottom];
            } else {
                diceNumber[bottom] = diceMap[ny][nx];
                diceMap[ny][nx] = 0;
            }

            y = ny;
            x = nx;
            System.out.println(diceNumber[nowNum]);
        }
    }
}