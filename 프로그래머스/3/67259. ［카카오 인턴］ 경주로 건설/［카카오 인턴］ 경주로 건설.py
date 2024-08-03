# 벽이 있으면 건설 불가 1
# 이동은 직선 도로 100
# 직각으로 만나면 코너 500
# 최소 비용 구하기

import sys

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]
min_cost = sys.maxsize

def dfs(board, n, visited, x, y, cost, direction):
    global min_cost, min_dis
    if min_cost <= cost:
        return
    
    if x == n-1 and y == n-1:
        min_cost = min(min_cost, cost)
        return
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if nx < 0 or ny < 0 or nx >= n or ny >= n or board[nx][ny] == 1:
            continue
            
        if (nx, ny) in visited:
            continue
                 
        n_cost = cost + 100
        if (direction + i) % 2 == 1:
            n_cost += 500
        
        visited.append((nx, ny))
        dfs(board, n, visited, nx, ny, n_cost, i)
        visited.remove((nx, ny))
    

def solution(board):
    dfs(board, len(board), [], 0, 0, 0, 2)
    dfs(board, len(board), [], 0, 0, 0, 1)
    return min_cost