import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int N, M;
    static boolean[] visited;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        count = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            visited[i] = true;

            while (!queue.isEmpty()) {
                int current = queue.poll();
                for (int next : graph[current]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                        count[next]++;
                    }
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, count[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (count[i] == max) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);
    }
}