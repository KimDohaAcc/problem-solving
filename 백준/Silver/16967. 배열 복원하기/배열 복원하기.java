import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[][] arrB = new int[H + X][W + Y];
        for (int i = 0; i < H + X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W + Y; j++) {
                arrB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] arrA = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (i < X || j < Y) {
                    arrA[i][j] = arrB[i][j];
                } else if (i + X >= H || j + Y >= W) {
                    arrA[i][j] = arrB[i + X][j + Y];
                } else {
                    arrA[i][j] = arrB[i][j] - arrA[i - X][j - Y];
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(arrA[i][j] + " ");
            }
            System.out.println();
        }
    }
}