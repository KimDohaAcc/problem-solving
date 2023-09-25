import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        p = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            makeset(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i < p.length; i++) {
            if (!answer.contains(findset(i))) {
                answer.add(p[i]);
            }
        }
        System.out.println(answer.size());
    }

    static void makeset(int x) {
        p[x] = x;
    }

    static int findset(int x) {
        if (p[x] == x) {
            return x;
        } else {
            return p[x] = findset(p[x]);
        }
    }

    static void union(int x, int y) {
        p[findset(y)] = findset(x);
    }
}