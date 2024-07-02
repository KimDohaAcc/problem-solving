class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        double angS = 360/60; // 1초에 초침이 움직이는 각도
        double angM = 6.0/60; // 1초에 분침이 움직이는 각도
        double angH =0.5/60; // 1초에 시침이 움직이는 각도
                
        int startTime = s1 + m1 * 60 + h1 * 60 * 60;
        int endTime = s2 + m2 * 60 + h2 * 60 * 60;
        
        double h = (startTime * angH) % 360;
        double m = (startTime * angM) % 360;
        double s = (startTime * angS) % 360;
        
        int cnt = 0;
        
        if(h == m && m == s) {
            cnt ++;
        }
        
        for(int i = startTime + 1; i <= endTime; i ++) {
            double nowH = (i * angH) % 360;
            double nowM = (i * angM) % 360;
            double nowS = (i * angS) % 360 == 0 ? i * angS : (i * angS) % 360;
            
            if(s < h && nowH <= nowS) {
                cnt ++;
            }

            if(s < m && nowM <= nowS && nowM != nowH) {
                cnt ++;
            }
                
            h = nowH % 360;
            m = nowM % 360;
            s = nowS % 360;
        }
        
        
        return cnt;
    }
}