import java.util.*;

class Solution {

    static class Answer {
        int[] arrows;
        int score;
        
        public Answer(int[] arrows, int score) {
            this.arrows = arrows;
            this.score = score;
        }
        
        public void setAnswer(int[] arrows, int score) {
            this.arrows = arrows;
            this.score = score;
        }
    }
    
    static int N;
    static int[] apeach;
    static Answer answer = new Answer(new int[] {-1}, 0);
    
    public int[] solution(int n, int[] info) {
        // 라이언이 불쌍한 문제
        N = n;
        apeach = info;
        dfs(0, 0, new int [11]);
        return answer.arrows;
    }
    
    static int checkScore(int[] arrows) {
        int apeachScore = 0;
        int lionScore = 0;
        
        for(int i = 0; i < 10; i ++) {
            if(apeach[i] < arrows[i]) {
                lionScore += 10 - i;
            } else {
                if(apeach[i] > 0) {
                    apeachScore += 10 - i;
                }
            }
        }
        
        return lionScore <= apeachScore ? -1 : lionScore - apeachScore;
    }
    
    static void dfs(int start, int cnt, int[] arrows) {
        if(cnt == N) {
            int score = checkScore(arrows);
            
            if(score == -1) {
                return;
            }
            
            if(answer.score == score) {
                for(int i = 10; i >= 0; i --) {
                    if(answer.arrows[i] < arrows[i]) {
                        answer.setAnswer(arrows, score);
                        break;
                    } else if(answer.arrows[i] > arrows[i]) {
                        break;
                    }
                }
            }
            
            else if(answer.score < score) {
                answer.setAnswer(arrows, score);
            }
            
            return;
        }
        
        for(int i = start; i < 11; i ++) {
            if(i < 10 && cnt + apeach[i] + 1 <= N) {
                arrows[i] = apeach[i] + 1;
                dfs(i + 1, cnt + apeach[i] + 1, Arrays.copyOf(arrows, 11));
                arrows[i] = 0;
            }

            else if(i == 10) {
                arrows[i] = N - cnt;
                dfs(i + 1, N, Arrays.copyOf(arrows, 11));
                arrows[i] = 0;
            }
        }
    }
}