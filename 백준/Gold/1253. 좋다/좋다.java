import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Long> list = new ArrayList<>();
        int checkZero = 0;

        for (int i = 0; i < N; i++) {
            Long num = Long.parseLong(st.nextToken());
            if (num == 0L) {
                checkZero++;
                continue;
            }

            list.add(num);
        }


        Set<Long> plus = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Long plusNum = list.get(i) + list.get(j);
                plus.add(plusNum);
            }
        }

        int cnt = 0;
        if ((checkZero > 0 && plus.contains(0L)) || checkZero >= 3) {
            cnt += checkZero;
        }

        for (int i = 0; i < list.size(); i++) {
            if (plus.contains(list.get(i))) {
                cnt++;
            } else if (checkZero > 0) {
                Long num = list.get(i);
                list.remove(i);
                if (list.contains(num)) {
                    cnt++;
                }
                list.add(i, num);
            }
        }


        System.out.println(cnt);
    }
}