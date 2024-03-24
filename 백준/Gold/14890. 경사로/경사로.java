import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static int[][] map;
    static int N;
    static int L;
    static boolean[][] isThereStair;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        isThereStair = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (findRoad(i, 0, false)) {
                cnt++;
            }

            if (findRoad(0, i, true)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static boolean findRoad(int y, int x, boolean flag) {
        int[] height = new int[N];
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!flag) {
                height[i] = map[y][i];
            } else {
                height[i] = map[i][x];
            }
        }

        for (int i = 0; i < N - 1; i++) {
            if (height[i] - 1 == height[i + 1]) {
                for (int j = i + 1; j < i + 1 + L; j++) {
                    if (j >= N || visited[j] || height[i + 1] != height[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            } else if (height[i] + 1 == height[i + 1]) {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || visited[j] || height[i] != height[j]) {
                        return false;
                    }
                    visited[j] = true;
                }
            } else if (height[i] != height[i + 1]) {
                return false;
            }
        }

        return true;
    }
}