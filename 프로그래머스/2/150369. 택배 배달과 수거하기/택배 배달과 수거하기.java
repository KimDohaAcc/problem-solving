class Solution {
    static int capCnt;
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 어차피 오고 가는 거리는 동일하다
        // 뒷 집부터 배달하기
        // 포인터를 줘서 다음 방문해야 할 집을 표시해두자
        capCnt = cap;
        
        int pickUpPointer = -1;
        int deliveryPointer = -1;
        boolean flagP = false;
        boolean flagD = false;
        
        // 포인터 세팅
        for(int i = n - 1; i >= 0; i --) {
            if(!flagD && deliveries[i] > 0) {
                flagD = true;
                deliveryPointer = i;
            }
            
            if(!flagP && pickups[i] > 0) {
                flagP = true;
                pickUpPointer = i;
            }
            
            if(flagD && flagP) {
                break;
            }
        }

        long answer = 0;
        
        while(true) {
            if(deliveryPointer == -1 && pickUpPointer == -1) {
                break;
            }
            
            answer += Math.max(deliveryPointer + 1, pickUpPointer + 1) * 2;
            
            deliveryPointer = visit(deliveries, deliveryPointer);
            pickUpPointer = visit(pickups, pickUpPointer);
        }
        
        return answer;
    }
    
    static int visit(int[] arr, int pointer) {
        int cnt = capCnt;
        while(pointer >= 0) {
            if(arr[pointer] > 0 && cnt > 0) {
                if(cnt >= arr[pointer]) {
                    cnt -= arr[pointer];
                    arr[pointer] = 0;
                    pointer --;
                } else {
                    arr[pointer] -= cnt;
                    cnt = 0;
                }
            }
            
            else if(arr[pointer] > 0 && cnt == 0) {
                break;
            }

            else {
                pointer --;
            }
        }
        
        return pointer;
    }
}