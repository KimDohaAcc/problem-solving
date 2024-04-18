import java.util.*;

class Solution {
    static int maxPlusUser = 0;
    static int maxSell = 0;
    public int[] solution(int[][] users, int[] emoticons) {
        chooseDiscount(users, new int[emoticons.length], emoticons, 0, emoticons.length);
        
        int[] answer = {maxPlusUser, maxSell};
        return answer;
    }
    
    static void calculate(int[][] users, int[] eDiscount, int[] emoticons) {
        // 할인율에 따른 가격합 구하기
        int[] sellPrice = new int[4]; // 10 20 30 40
        
        for(int i = 0; i < emoticons.length; i ++) {
            sellPrice[eDiscount[i] / 10 - 1] += emoticons[i] / 100 * (100 - eDiscount[i]);
        }
        
        // 사용자 계산
        int plusUser = 0;
        int totalSell = 0;
        
        for(int i = 0; i < users.length; i ++) {
            int buyPrice = 0;
            
            // 비율, 가격
            if(users[i][0] <= 10) {
                buyPrice += sellPrice[0];
            }
            
            if(users[i][0] <= 20) {
                buyPrice += sellPrice[1];
            }
            
            if(users[i][0] <= 30) {
                buyPrice += sellPrice[2];
            }
            
            if(users[i][0] <= 40) {
                buyPrice += sellPrice[3];
            }
            
            if(buyPrice >= users[i][1]) {
                plusUser ++;
            } else {
                totalSell += buyPrice;
            }
        }
        
        if(maxPlusUser < plusUser) {
            maxPlusUser = plusUser;
            maxSell = totalSell;
        } else if(maxPlusUser == plusUser && maxSell < totalSell) {
            maxSell = totalSell;
        }
    }
    
    static void chooseDiscount(int[][] users, int[] eDiscount, int[] emoticons, int turn, int emoticonLength) {
        if(turn == emoticonLength) {
            // 사용자 구매 확인
            calculate(users, eDiscount, emoticons);
        }
        
        // 할인율 선택
        for(int i = turn; i < emoticonLength; i ++) {
            if(eDiscount[i] != 0) {
                continue;
            }
            
            eDiscount[i] = 10;
            chooseDiscount(users, eDiscount, emoticons, turn + 1, emoticonLength);
            
            eDiscount[i] = 20;
            chooseDiscount(users, eDiscount, emoticons, turn + 1, emoticonLength);
            eDiscount[i] = 30;
            chooseDiscount(users, eDiscount, emoticons, turn + 1, emoticonLength);
            eDiscount[i] = 40;
            chooseDiscount(users, eDiscount, emoticons, turn + 1, emoticonLength);
            eDiscount[i] = 0;
        }
    }
}