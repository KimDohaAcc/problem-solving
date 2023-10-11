import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

// 최소 횟수로 목적지까지 가는 법
// 인접한 곳을 방문하며 최소로 가는 거 -> bfs
public class Main {
    static int[] dy = {-2, -2, 2, 2, -1, -1, 1, 1, 1, -1, 0, 0};
    static int[] dx = {-1, 1, -1, 1, -2, 2, -2, 2, 0, 0, 1, -1};
    static final int OBSTACLE = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][W];

        for (int i = 0; i < H; i++) {
            map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        queue.offer(new int[]{0, 0, 0, 0});
        int min = -1;
        boolean[][][] visited = new boolean[H][W][K + 1];
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int y = idx[0];
            int x = idx[1];
            int dis = idx[2];
            int k = idx[3];

            if (y == H - 1 && x == W - 1) {
                min = dis;
                break;
            }

            for (int i = 0; i < dy.length; i++) {
                if (k == K && i == 0) {
                    i = 7;
                    continue;
                }

                if (y + dy[i] < 0 || y + dy[i] >= H || x + dx[i] < 0 || x + dx[i] >= W) {
                    continue;
                }

                if (map[y + dy[i]][x + dx[i]] == OBSTACLE) {
                    continue;
                }

                if (i < 8 && visited[y + dy[i]][x + dx[i]][k + 1]) {
                    continue;
                }

                if (i >= 8 && visited[y + dy[i]][x + dx[i]][k]) {
                    continue;
                }

                visited[y + dy[i]][x + dx[i]][i < 8 ? k + 1 : k] = true;
                queue.offer(new int[]{y + dy[i], x + dx[i], dis + 1, i < 8 ? k + 1 : k});
            }
        }

        System.out.println(min);
    }
}