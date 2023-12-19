import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        List<Integer> fullList = new ArrayList<>();
        Collections.addAll(fullList, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            switch (input[0]) {
                case "add":
                    if (!list.contains(Integer.parseInt(input[1]))) list.add(Integer.parseInt(input[1]));
                    break;
                case "remove":
                    if (list.contains(Integer.parseInt(input[1])))
                        list.remove(Integer.valueOf(Integer.parseInt(input[1])));
                    break;
                case "check":
                    sb.append(list.contains(Integer.parseInt(input[1])) ? 1 : 0).append('\n');
                    break;
                case "toggle": {
                    if (list.contains(Integer.parseInt(input[1])))
                        list.remove(Integer.valueOf(Integer.parseInt(input[1])));
                    else list.add(Integer.parseInt(input[1]));
                }
                     break;
                case "all": {
                    list.clear();
                    list.addAll(fullList);
                }
                     break;
                case "empty":
                    list.clear();
                    break;
            }
        }

        System.out.print(sb);
    }
}