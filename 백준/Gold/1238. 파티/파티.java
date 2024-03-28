import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 가중치 양수
    // X부터 다른 모든 지점까지 가는데 드는 최단 비용을 구한다
    // 다익스트라 문제

    static class Node implements Comparable<Node> {
        int start;
        int end;
        int value;

        public Node(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Node>[] list = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            list[start].add(new Node(start, end, value));
            list[end].add(new Node(start, end, value));
        }

        Queue<Node> pq = new PriorityQueue<>();
        int[] dis = new int[N + 1];
        pq.offer(new Node(X, X, 0));

        Arrays.fill(dis, 200001);
        dis[0] = 0;
        dis[X] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < list[node.end].size(); i++) {
                Node next = list[node.end].get(i);

                if (next.start != node.end) {
                    continue;
                }

                if (dis[next.end] > dis[node.end] + next.value) {
                    dis[next.end] = dis[node.end] + next.value;
                    pq.offer(new Node(next.start, next.end, dis[next.end]));
                }
            }
        }

        pq = new PriorityQueue<>();
        int[] disR = new int[N + 1];
        Arrays.fill(disR, 200001);
        pq.offer(new Node(X, X, 0));
        disR[0] = 0;
        disR[X] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < list[node.start].size(); i++) {
                Node before = list[node.start].get(i);
                if (node.start != before.end) {
                    continue;
                }

                if (disR[before.start] > disR[node.start] + before.value) {
                    disR[before.start] = disR[node.start] + before.value;
                    pq.offer(new Node(before.start, before.end, disR[before.start]));
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            dis[i] += disR[i];
        }

//        System.out.println(Arrays.toString(dis));
        System.out.println(Arrays.stream(dis).max().getAsInt());
    }
}