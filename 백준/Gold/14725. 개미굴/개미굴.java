import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

public class Main {
    static class Trie {
        Map<String, Trie> child;

        Trie() {
            this.child = new TreeMap<>();
        }

        void print(int depth) {
            for (Map.Entry<String, Trie> entry : child.entrySet()) {
                System.out.println("--".repeat(depth) + entry.getKey());
                entry.getValue().print(depth + 1);
            }
        }
    }

    static int N;
    static Function<String, Integer> parsing = Integer::parseInt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parsing.apply(br.readLine());
        StringTokenizer st;

        Trie root = new Trie();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = parsing.apply(st.nextToken());

            Trie node = root;
            for (int j = 0; j < K; j++) {
                node = node.child.computeIfAbsent(st.nextToken(), s -> new Trie());
            }
        }

        root.print(0);
    }
}