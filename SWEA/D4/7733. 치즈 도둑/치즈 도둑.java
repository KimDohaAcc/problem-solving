import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][] cheese;
    static int day;

    static int max;

    static int N;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            System.out.print("#" + tc + " ");
            N = scan.nextInt();
            cheese = new int[N][N];
            day = 1;
            max = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cheese[i][j] = scan.nextInt();
                }
            }
            while (day <= 100) {
                max = Math.max(max, eatCheese(new boolean[N][N]));
                day++;
            }

            System.out.println(max);
        }
    }

    static int eatCheese(boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        int group = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || cheese[i][j] <= day) {
                    continue;
                }

                visited[i][j] = true;
                queue.offer(new int[]{i, j});

                while (!queue.isEmpty()) {
                    int[] arr = queue.poll();
                    int y = arr[0];
                    int x = arr[1];

                    for (int k = 0; k < 4; k++) {
                        if (y + dy[k] < 0 || y + dy[k] >= N || x + dx[k] < 0 || x + dx[k] >= N) {
                            continue;
                        }

                        if (!visited[y + dy[k]][x + dx[k]] && cheese[y + dy[k]][x + dx[k]] > day) {
                            visited[y + dy[k]][x + dx[k]] = true;
                            queue.offer(new int[]{y + dy[k], x + dx[k]});
                        }
                    }
                }

                group++;
            }
        }

        return group;
    }
}