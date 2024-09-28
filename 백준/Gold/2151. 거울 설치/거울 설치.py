import sys
from heapq import heappush, heappop

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

dir_dict = dict()
dir_dict[(0, -1)] = 0
dir_dict[(0, 1)] = 1
dir_dict[(-1, 0)] = 2
dir_dict[(1, 0)] = 3

N = int(input())
room = [list(input()) for _ in range(N)]
visited = [[[sys.maxsize] * 4 for _ in range(N)] for _ in range(N)]
hq = []
start = (0, 0)
# 문부터 문까지 최소 거울 설치 개수
flag = False
for i in range(N):
    for j in range(N):
        if not flag and room[i][j] == '#':
            start = (i, j)
            # 사방 탐색
            for k in range(4):
                nx = i + dx[k]
                ny = j + dy[k]

                if nx < 0 or ny < 0 or nx >= N or ny >= N:
                    continue

                if room[nx][ny] != '*':
                    dxx = dx[k]
                    dyy = dy[k]
                    flag = True
                    heappush(hq, (0, i, j, dx[k], dy[k]))

while hq:
    count, x, y, dxx, dyy = heappop(hq)
    v = dir_dict[(dxx, dyy)]
    visited[x][y][v] = count

    if room[x][y] == '#' and not (x == start[0] and y == start[1]):
        # 골인
        print(count)
        break

    nx = x + dxx
    ny = y + dyy

    if nx < 0 or ny < 0 or nx >= N or ny >= N:
        continue

    if room[nx][ny] == '*':
        continue

    if visited[nx][ny][v] > count:
        heappush(hq, (count, nx, ny, dxx, dyy))

    if room[nx][ny] == '!':
        # 방향 꺾기
        new_dx = -1 * dyy
        new_dy = -1 * dxx

        v = dir_dict[(new_dx, new_dy)]
        if visited[nx][ny][v] > count + 1:
            heappush(hq, (count + 1, nx, ny, new_dx, new_dy))

        new_dx *= -1
        new_dy *= -1

        v = dir_dict[(new_dx, new_dy)]
        if visited[nx][ny][v] > count + 1:
            heappush(hq, (count + 1, nx, ny, new_dx, new_dy))