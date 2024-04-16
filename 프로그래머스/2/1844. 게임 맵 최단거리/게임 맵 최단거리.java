import java.util.*;
import java.io.*;

class Solution {
    int[] dy = {-1, 1, 0, 0};
    int[] dx = {0, 0, -1, 1};
    public int solution(int[][] maps) {
        int min = Integer.MAX_VALUE;
        Queue<int[]> queue = new ArrayDeque<>();
        int garo = maps.length;
        int sero = maps[0].length;
        boolean[][] visited = new boolean [garo][sero];
        queue.offer(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()) {
            int[] idx = queue.poll();
            
            for(int i = 0; i < 4; i ++) {
                int ny = idx[0] + dy[i];
                int nx = idx[1] + dx[i];
                
                if(ny < 0 || ny >= garo || nx < 0 || nx >= sero) {
                    continue;
                }
                
                if(visited[ny][nx] || maps[ny][nx] == 0) {
                    continue;
                }
                
                if(ny == garo - 1 && nx == sero - 1) {
                    min = idx[2] + 1;   
                }
                
                visited[ny][nx] = true;
                queue.offer(new int[] {ny, nx, idx[2] + 1});
            }
        }
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}