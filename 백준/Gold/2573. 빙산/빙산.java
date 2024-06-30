import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] iceMountain;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceMountain = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                iceMountain[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] count = new int[N][M];
        int year = 0;

        Loop:
        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (iceMountain[i][j] > 0) {
                        year++;
                        bfs(i, j, count, year);
//                        print();
                        int res = check(year);
                        if (res >= 0) {
                            System.out.println(res);
                            return;
                        } else {
                            continue Loop;
                        }
                    }
                }
            }
        }
    }

    static int check(int year) {
        boolean[][] visited = new boolean[N][M];
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (flag && iceMountain[i][j] > 0 && !visited[i][j]) {
//                    print(visited);
                    return year;
                }

                if (!flag && iceMountain[i][j] > 0) {
                    flag = true;
                    Queue<int[]> queue = new LinkedList<>();
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] idx = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int ny = idx[0] + dy[k];
                            int nx = idx[1] + dx[k];

                            if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                                continue;
                            }

                            if (visited[ny][nx]) {
                                continue;
                            }

                            if (iceMountain[ny][nx] > 0) {
                                visited[ny][nx] = true;
                                queue.offer(new int[]{ny, nx});
                            }
                        }
                    }
                }
            }
        }

        return !flag ? 0 : -1;
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(iceMountain[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void print(boolean[][] visited) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void bfs(int y, int x, int[][] count, int year) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{y, x});
        count[y][x]++;

        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            temp[i] = Arrays.copyOf(iceMountain[i], M);
        }

        while (!queue.isEmpty()) {
            int[] idx = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = idx[0] + dy[i];
                int nx = idx[1] + dx[i];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                    continue;
                }

                if (iceMountain[ny][nx] > 0 && count[ny][nx] < year) {
                    count[ny][nx]++;
                    queue.offer(new int[]{ny, nx});
                }

                if (temp[idx[0]][idx[1]] > 0 && iceMountain[ny][nx] == 0) {
                    temp[idx[0]][idx[1]]--;
                }
            }
        }

        iceMountain = temp;
    }
}