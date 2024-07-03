import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        // 이 익숙한 전개는 그리디...?
        
        List<int[]> bookingList = new ArrayList<>();
        
        for(int i = 0; i < book_time.length; i ++) {
            StringBuilder sb = new StringBuilder(book_time[i][0]);
            int hour = Integer.parseInt(sb.substring(0, 2));
            int min = Integer.parseInt(sb.substring(3, 5));
            int enterTime = hour * 60 + min;
            
            sb = new StringBuilder(book_time[i][1]);
            hour = Integer.parseInt(sb.substring(0, 2));
            min = Integer.parseInt(sb.substring(3, 5));
            int leaveTime = hour * 60 + min;
            
            bookingList.add(new int[] {enterTime, leaveTime});
        }
        
        Collections.sort(bookingList, new Comparator<int[]>() {
            
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
            
        Queue<Integer> rooms = new PriorityQueue<>(Comparator.naturalOrder());
        
        for(int[] booking : bookingList) {
            if(!rooms.isEmpty() && rooms.peek() <= booking[0]) {
                rooms.poll();
            }

            rooms.offer(booking[1] + 10);
        }
        
        return rooms.size();
    }
}