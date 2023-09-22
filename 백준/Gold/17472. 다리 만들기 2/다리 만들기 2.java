import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int islandCnt;
    static int[][] island;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int[][][] bridgeInfo;
    static int min;

    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        // 입력
        island = new int[N][M];
        for (int n = 0; n < N; n++) {
            island[n] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 섬 색칠(bfs)
        Queue<int[]> queue = new LinkedList<>();
        int color = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (island[i][j] != 1)
                    continue;

                queue.add(new int[]{i, j});
                while (!queue.isEmpty()) {
                    int[] arr = queue.poll();
                    island[arr[0]][arr[1]] = color;

                    for (int k = 0; k < 4; k++) {
                        if (arr[0] + dy[k] < 0 || arr[0] + dy[k] >= N || arr[1] + dx[k] < 0 || arr[1] + dx[k] >= M) {
                            continue;
                        }

                        // 인접한 곳이 섬이면 넣기
                        if (island[arr[0] + dy[k]][arr[1] + dx[k]] == 1) {
                            queue.offer(new int[]{arr[0] + dy[k], arr[1] + dx[k]});
                        }
                    }
                }

                color++;
            }
        }

        islandCnt = color;
        // 0 : 출발섬 1 : 도착섬 2 : 비용
        bridgeInfo = new int[islandCnt][islandCnt][1];

        // 각 섬을 잇는 다리를 구하기
        // 우하향 하면서 내려감
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (island[i][j] != 0 && j + 1 < M && island[i][j + 1] == 0) {
                    int x = j + 1;
                    while (x < M && island[i][x] == 0) {
                        x++;
                    }

                    if (x < M && x - j - 1 >= 2 && island[i][x] != island[i][j]) {
                        if (bridgeInfo[island[i][j]][island[i][x]][0] == 0 || bridgeInfo[island[i][j]][island[i][x]][0] > x - j - 1) {
                            bridgeInfo[island[i][j]][island[i][x]][0] = x - j - 1;
                        }
                    }
                }

                if (island[i][j] != 0 && i + 1 < N && island[i + 1][j] == 0) {
                    int y = i + 1;
                    while (y < N && island[y][j] == 0) {
                        y++;
                    }

                    if (y < N && y - i - 1 >= 2 && island[y][j] != island[i][j]) {
                        if (bridgeInfo[island[i][j]][island[y][j]][0] == 0 || bridgeInfo[island[i][j]][island[y][j]][0] > y - i - 1) {
                            bridgeInfo[island[i][j]][island[y][j]][0] = y - i - 1;
                        }
                    }
                }
            }
        }

        // 다리 정보를 바탕으로 섬을 연결하자
        // 간선리스트
        ArrayList<int[]> list = new ArrayList<>();
        // 비용은 어따 저장
        for (int i = 2; i < islandCnt; i++) {
            for (int j = 2; j < islandCnt; j++) {
                if (bridgeInfo[i][j][0] != 0) {
                    // 양방향
                    list.add(new int[]{i, j, bridgeInfo[i][j][0]});
                }
            }
        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        p = new int[islandCnt];

        int cost = 0;

        for (int i = 2; i < islandCnt; i++) {
            p[i] = i;
        }

        for (int i = 0; i < list.size(); i++) {
            int[] x = list.get(i);

            if (findset(x[0]) != findset(x[1])) {
                union(x[0], x[1]);
                cost += x[2];
            }
        }

        int boss = findset(2);
        for (int i = 3; i < islandCnt; i++) {
            if (findset(i) != boss) {
                cost = -1;
            }
        }

        System.out.println(cost);
    }

    static void union(int x, int y) {
        p[findset(y)] = findset(x); // x의 대표를 y의 대표로 넣겠다. 랭크 고려x
    }

    static int findset(int x) {
        if (x != p[x])
            p[x] = findset(p[x]);
        return p[x];
    }
}