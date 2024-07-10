import java.util.*;

class Solution {
    // 내려오든지 말든지 그냥 산봉우리까지 최단 거리 구하면 댄다
    // 다익스트라 쓰는데 거리 말고 intensity 넣으면 될듯?
    static int intensity = 10000001;
    static int summit = 0;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<int[]>[] nodeList = new ArrayList [n + 1];

        for(int i = 0; i < nodeList.length; i ++) {
            nodeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < paths.length; i ++) {
            int node = paths[i][0];
            int other = paths[i][1];
            int dis = paths[i][2];

            nodeList[node].add(new int [] {other, dis});
            nodeList[other].add(new int [] {node, dis});
        }

        // 게이트 찾기 좋게 list로 바꿈
        List<Integer> gateList = new ArrayList<>();
        for(int i = 0; i < gates.length; i ++) {
            gateList.add(gates[i]);
        }

        // 번호가 낮은 산봉우리부터 탐색
        Arrays.sort(summits);
        
        // 산봉우리 찾기 좋게 list로 바꿈
        List<Integer> summitList = new ArrayList<>();
        for(int i = 0; i < summits.length; i ++) {
            summitList.add(summits[i]);
        }

        // 다익스트라
        dijkstra(nodeList, gateList, summitList, n);

        // 산봉우리 번호랑 intensity 넣기
        int[] answer = {summit, intensity};
        return answer;
    }

    static void dijkstra(List<int[]>[] nodeList, List<Integer> gateList, List<Integer> summitList, int n) {
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] == o2[2] ? o1[1] - o2[1] : o1[2] - o2[2];
            }
        });

        int[] dis = new int [n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);

        for (Integer summit : summitList) {
            pq.offer(new int[] {summit, 0, summit});
            dis[summit] = 0;
        }

        while(!pq.isEmpty()) {
            int[] arr = pq.poll();
            int now = arr[0];
            int s = arr[2];

            for(int i = 0; i < nodeList[now].size(); i ++) {
                int[] node = nodeList[now].get(i);
                int next = node[0];
                int nextD = node[1];
                int nowInt = Math.max(dis[now], nextD);

                if(summitList.contains(next) || dis[next] < nowInt || nowInt >= intensity) {
                    continue;
                }

                if(dis[next] > nowInt) {
                    dis[next] = nowInt;
                    pq.offer(new int[] {next, nextD, s});
                }

                if(gateList.contains(next) && dis[next] < intensity) {
                    intensity = dis[next];
                    summit = s;
                }

            }
        }
    }
}