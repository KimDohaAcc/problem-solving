import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] stars = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            stars[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                int ct = 0;
                int tx = stars[i][0];
                int ty = stars[j][1];

                for (int[] star : stars) {
                    if (!isBound(tx, ty, star[0], star[1], l)) {
                        ct++;
                    }
                }

                answer = Math.min(ct, answer);
            }
        }

        System.out.println(answer);
    }

    public static boolean isBound(int tx, int ty, int sx, int sy, int l) {
        return sx >= tx && sx <= tx + l && sy >= ty && sy <= ty + l;
    }
}