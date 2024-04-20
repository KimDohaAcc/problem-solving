import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        if (N == 1 || (N >= 3 && ((N / 3 % 2 == 1) && (N % 3 % 2 == 0)))
                || (N / 3 % 2 == 0 && (N % 3 % 2 == 1))) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}