import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int R;
    static int C;
    static int K;
    static int W;
    static Map<Integer, List<int[]>> wallMap;
    static final int EMPTY = 0;
    static final int RIGHT = 1;
    static final int LEFT = 2;
    static final int UP = 3;
    static final int DOWN = 4;
    static final int CHECKBLOCK = 5;
    static List<int[]> heaters = new ArrayList<>();
    static List<int[]> checkBlocks = new ArrayList<>();
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int chocolate;
    static int[][] temperature;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] room = new int[R][C];
        temperature = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());

                if (room[i][j] > EMPTY && room[i][j] < CHECKBLOCK) {
                    heaters.add(new int[]{i, j});
                } else if (room[i][j] == CHECKBLOCK) {
                    checkBlocks.add(new int[]{i, j});
                }
            }
        }

        W = Integer.parseInt(br.readLine());
        wallMap = new HashMap<>();

        wallMap.put(RIGHT, new ArrayList<>());
        wallMap.put(LEFT, new ArrayList<>());
        wallMap.put(UP, new ArrayList<>());
        wallMap.put(DOWN, new ArrayList<>());

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) {
                wallMap.get(UP).add(new int[]{y, x});
                wallMap.get(DOWN).add(new int[]{y - 1, x});
            } else {
                wallMap.get(RIGHT).add(new int[]{y, x});
                wallMap.get(LEFT).add(new int[]{y, x + 1});
            }
        }

        while (!flag) {
            warmWind(room);

            int[][] temp = adjustTemperature();
            adjustTemperature2(temp);
            chocolate++;

            // 조사
            if (!checkTemperature() || chocolate == 101) {
                flag = true;
                System.out.println(chocolate);
            }

        }
    }

    private static boolean checkTemperature() {
        for (int[] idx : checkBlocks) {
            if (temperature[idx[0]][idx[1]] < K) {
                return true;
            }
        }
        return false;
    }

    private static void adjustTemperature2(int[][] temp) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                temperature[i][j] += temp[i][j];

                if ((i == 0 || j == 0 || i == R - 1 || j == C - 1) && temperature[i][j] > 0) {
                    temperature[i][j]--;
                }
            }
        }
    }

    private static int[][] adjustTemperature() {
        // 온도 조절
        int[][] temp = new int[R][C];
        boolean[][][] visited = new boolean[R][C][5];

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i];
                    int nx = x + dx[i];

                    if (rangeCheck(ny, nx)) {
                        int pTemp = temperature[y][x];
                        int nTemp = temperature[ny][nx];
                        int adjust = Math.abs(pTemp - nTemp) / 4;
                        int opposite = (i + 1) % 2 == 0 ? i : i + 2;

                        if (pTemp != nTemp && isNotWall(y, x, i + 1) && !visited[ny][nx][opposite]) {
                            temp[y][x] += pTemp > nTemp ? -adjust : adjust;
                            temp[ny][nx] += pTemp > nTemp ? adjust : -adjust;
                            visited[y][x][i + 1] = true;
                        }
                    }
                }
            }
        }

        return temp;
    }

    private static boolean isNotWall(int y, int x, int dir) {
        return wallMap.get(dir).stream().noneMatch(wall -> wall[0] == y && wall[1] == x);
    }

    private static void warmWind(int[][] room) {
        // 온풍기 바람
        for (int[] idx : heaters) {
            boolean[][] visited = new boolean[R][C];
            int dir = room[idx[0]][idx[1]];
            Queue<int[]> queue = new ArrayDeque<>();
            if (!rangeCheck(idx[0] + dy[dir - 1], idx[1] + dx[dir - 1])) {
                continue;
            }

            temperature[idx[0] + dy[dir - 1]][idx[1] + dx[dir - 1]] += 5;
            queue.offer(new int[]{idx[0] + dy[dir - 1], idx[1] + dx[dir - 1], 4});

            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                int ny = arr[0] + dy[dir - 1];
                int nx = arr[1] + dx[dir - 1];
                int nowTemp = arr[2];

                // 직진
                if (rangeCheck(ny, nx) && !visited[ny][nx] && isNotWall(arr[0], arr[1], dir)) {
                    visited[ny][nx] = true;
                    temperature[ny][nx] += nowTemp;

                    if (nowTemp != 0) {
                        queue.offer(new int[]{ny, nx, nowTemp - 1});
                    }
                }

                int nDir = dir + 2 > 4 ? dir - 2 : dir + 2;
                checkCross(queue, visited, dir - 1, arr, nDir - 1);

                nDir = nDir % 2 == 0 ? nDir - 1 : nDir + 1;
                checkCross(queue, visited, dir - 1, arr, nDir - 1);
            }
        }
    }

    private static void checkCross(Queue<int[]> queue, boolean[][] visited, int dir, int[] arr, int nDir) {
        int ny = arr[0] + dy[nDir];
        int nx = arr[1] + dx[nDir];
        int nowTemp = arr[2];
        if (rangeCheck(ny, nx) && isNotWall(arr[0], arr[1], nDir + 1)) {
            int nny = ny + dy[dir];
            int nnx = nx + dx[dir];
            if (rangeCheck(nny, nnx) && !visited[nny][nnx] && isNotWall(ny, nx, dir + 1)) {
                visited[nny][nnx] = true;
                temperature[nny][nnx] += nowTemp;

                if (nowTemp != 0) {
                    queue.offer(new int[]{nny, nnx, nowTemp - 1});
                }
            }
        }
    }

    static boolean rangeCheck(int y, int x) {
        return y < R && y >= 0 && x < C && x >= 0;
    }
}