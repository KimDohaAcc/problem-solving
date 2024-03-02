import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Fruit implements Comparable<Fruit> {

        String name;
        List<Fruit> child;

        public Fruit(String name) {
            this.name = name;
            child = new ArrayList<>();
        }

        public void addChild(Fruit child) {
            this.child.add(child);
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public int compareTo(Fruit o) {
            return this.name.compareTo(o.name);
        }
    }

    static List<List<Fruit>> list;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            Fruit parent = null;
            for (int j = 0; j < K; j++) {
                if (list.size() <= j) {
                    list.add(new ArrayList<>());
                }

                String NAME = st.nextToken();
                int IDX = j;
                Fruit PARENT = parent;

                parent = isFruitEmpty(IDX, NAME, PARENT).orElseGet(() -> {
                    Fruit newFruit = new Fruit(NAME);
                    list.get(IDX).add(newFruit);

                    if (IDX > 0) {
                        PARENT.addChild(newFruit);
                    }
                    return newFruit;
                });
            }
        }

        Collections.sort(list.get(0));
        for (int i = 0; i < list.get(0).size(); i++) {
            printDfs(0, list.get(0).get(i));
        }
    }

    static Optional<Fruit> isFruitEmpty(int j, String name, Fruit parent) {
        return list.get(j).stream().filter(fruit -> fruit.name.equals(name) && (parent == null || parent.child.contains(fruit))).findAny();
    }

    static void printDfs(int depth, Fruit fruit) {
        for (int i = 0; i < depth; i++) {
            System.out.print("--");
        }
        System.out.println(fruit.name);

        Collections.sort(fruit.child);
        for (int i = 0; i < fruit.child.size(); i++) {
            printDfs(depth + 1, fruit.child.get(i));
        }
    }
}