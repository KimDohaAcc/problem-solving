import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V;
    static int E;

    static List<Node>[] list;

    static int total = 0;

    static class Node implements Comparable<Node> {
        int to;
        int value;

        Node(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "to=" + to +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[V + 1];

        for (int i = 1; i < V + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            // C는 음수일 수도 있음
            int C = Integer.parseInt(st.nextToken());
            list[A].add(new Node(B, C));
            list[B].add(new Node(A, C));
        }

        Queue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];

        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.to]) {
                continue;
            }

            visited[node.to] = true;
            total += node.value;


            for (int i = 0; i < list[node.to].size(); i++) {
                Node next = list[node.to].get(i);

                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }

        System.out.println(total);
    }
}