import sys
import heapq

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def dijkstra(board, n):
    hq = list()
    dis = [[[sys.maxsize] * 4 for _ in range(n)] for _ in range(n)]
    heapq.heappush(hq, (0, 0, 0, 1))
    heapq.heappush(hq, (0, 0, 0, 2))
    
    dis[0][0][1] = 0
    dis[0][0][2] = 0
    
    while hq:
        cost, x, y, d = heapq.heappop(hq)
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            
            if nx < 0 or ny < 0 or nx >= n or ny >= n or board[nx][ny] == 1:
                continue
                
            n_cost = cost + 100 + (500 if (d + i) % 2 == 1 else 0)
            
            # d는 들어온 방향
            if dis[nx][ny][d] >= n_cost:
                dis[nx][ny][d] = n_cost
                heapq.heappush(hq, (n_cost, nx, ny, i))
                
    return min(dis[n-1][n-1])
        
def solution(board):
    return dijkstra(board, len(board))