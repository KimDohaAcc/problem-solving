import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
cctv가 가질 수 있는 모든 경우를 전부전부 다 해보기
 */
public class Main {
    static int[][] watchYou;
    static int N;
    static int M;
    static List<Integer> camera = new ArrayList<>();
    static List<int[]> cameraLocation = new ArrayList<>();
    static HashMap<String, String> turn = new HashMap<>();
    static List<String[]> cameraDir = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static int round = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        watchYou = new int[N][M];
        List<boolean[]> visited = new ArrayList<>();
        turn.put("U", "R");
        turn.put("R", "D");
        turn.put("D", "L");
        turn.put("L", "U");

        for (int i = 0; i < N; i++) {
            watchYou[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < M; j++) {
                if (watchYou[i][j] >= 1 && watchYou[i][j] <= 5) {
                    camera.add(watchYou[i][j]);
                    cameraLocation.add(new int[]{i, j});

                    switch (watchYou[i][j]) {
                        case 1:
                            cameraDir.add(new String[]{"R"});
                            visited.add(new boolean[4]);
                            break;
                        case 2:
                            cameraDir.add(new String[]{"L", "R"});
                            visited.add(new boolean[2]);
                            break;
                        case 3:
                            cameraDir.add(new String[]{"U", "R"});
                            visited.add(new boolean[4]);
                            break;
                        case 4:
                            cameraDir.add(new String[]{"L", "U", "R"});
                            visited.add(new boolean[4]);
                            break;
                        case 5:
                            cameraDir.add(new String[]{"D", "L", "U", "R"});
                            visited.add(new boolean[1]);
                            break;
                    }
                }
            }
        }

        iWatchYou(0, 0, new ArrayList<String[]>(cameraDir), visited);
        System.out.println(min);
    }

    static int[][] watchYouTemp() {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            temp[i] = watchYou[i].clone();
        }

        return temp;
    }

    static void iWatchYou(int cnt, int idx, List<String[]> cameraDirTemp, List<boolean[]> visited) {
        if (cnt == camera.size()) {
            int view = countView(watchYouTemp(), cameraDirTemp);
            min = Math.min(view, min);
            return;
        }

        boolean[] vis = visited.get(idx);
        for (int i = 0; i < vis.length; i++) {
            if (vis[i]) {
                continue;
            }

            // 이 방향으로 하겠다
            vis[i] = true;

            // 90도로 i번 돌리기
            // i는 각 방향마다 다르게 돌릴 수 있는 횟수
            for (int j = 0; j < i; j++) {
                // 확장 방향을 담은 배열
                String[] arr = new String[cameraDirTemp.get(idx).length];
                // 배열의 크기만큼 돌면서
                for (int k = 0; k < arr.length; k++) {
                    // 문자 하나를 뽑아서 90도로 돌림
                    String str = cameraDirTemp.get(idx)[k];
                    arr[k] = turn.get(str);
                }
                // 돌린 배열을 넣어주기
                cameraDirTemp.set(idx, arr);
            }

            // 하나를 뽑고 넘기기
            iWatchYou(cnt + 1, idx + 1, cameraDirTemp, visited);
            // 원상복구
            vis[i] = false;
            cameraDirTemp.set(idx, cameraDir.get(idx));
        }
    }

    static int countView(int[][] watchYouTemp, List<String[]> cameraDirTemp) {
        int[] cam = new int[2];

        for (int i = 0; i < cameraLocation.size(); i++) {
            cam[0] = cameraLocation.get(i)[0];
            cam[1] = cameraLocation.get(i)[1];

            // 탐색할 방향
            // i가 결정
            String[] strArr = cameraDirTemp.get(i);

            for (int j = 0; j < strArr.length; j++) {
                String str = strArr[j];
                int dy = 0;
                int dx = 0;
                int ny = cam[0];
                int nx = cam[1];

                if (str.equals("R")) {
                    dx += 1;
                } else if (str.equals("L")) {
                    dx -= 1;
                } else if (str.equals("U")) {
                    dy -= 1;
                } else if (str.equals("D")) {
                    dy += 1;
                }

                while (true) {
                    ny += dy;
                    nx += dx;

                    if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
                        break;
                    }

                    if (watchYouTemp[ny][nx] == 6) {
                        break;
                    }

                    if (watchYouTemp[ny][nx] == 0) {
                        watchYouTemp[ny][nx] = 9;
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (watchYouTemp[i][j] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}