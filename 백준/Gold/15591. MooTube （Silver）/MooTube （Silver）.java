import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 탐색을 위한 노드
    static class Node {
        int cost;
        int num;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    static ArrayList<Node>[] list;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        // 입력
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            list[p].add(new Node(q, r));
            list[q].add(new Node(p, r));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            // 기준 값
            int k = Integer.parseInt(st.nextToken());
            // 비디오 번호
            int v = Integer.parseInt(st.nextToken());

            // 비디오를 시작점으로 두고 탐색하기
            boolean[] visited = new boolean[N + 1];
            visited[v] = true;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(v);

            // 개수를 세는 배열
            int ans = 0;
            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (Node node : list[now]) {
                    // 다음 번호를 아직 방문하지 않았고
                    // 방문하는 비용이 k보다 작다면
                    // queue에 넣고 개수 올리기
                    if (!visited[node.num] && node.cost >= k) {
                        queue.offer(node.num);
                        visited[node.num] = true;
                        ans++;
                    }
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}