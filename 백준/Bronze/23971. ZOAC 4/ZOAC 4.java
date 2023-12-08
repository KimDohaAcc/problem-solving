import java.util.Scanner;

public class Main {
    static int H, W, N, M;
    static int cnt;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        H = scan.nextInt();
        W = scan.nextInt();
        N = scan.nextInt();
        M = scan.nextInt();

        cnt = (H % (N+1) == 0 ? H / (N+1) : H / (N+1) + 1) * (W % (M+1) == 0 ? W / (M+1) : W / (M+1) + 1);

        System.out.println(cnt);
    }
}