from collections import deque

N = int(input())
drawing = [list(input()) for _ in range(N)]
visited = [[[False, False] for _ in range(N)] for _ in range(N)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(x, y, color, drawing, visited, N):
    queue = deque()
    queue.append((x, y, 0))
    visited[x][y][0] = True
    flag = 0

    while queue:
        x, y, turn = queue.popleft()

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if 0 <= nx < N and 0 <= ny < N:
                if turn == 0 and not visited[nx][ny][0] and drawing[nx][ny] == color:
                    visited[nx][ny][0] = True

                    if drawing[nx][ny] == 'B':
                        visited[nx][ny][1] = True

                    queue.append((nx, ny, 0))

                if not visited[nx][ny][1] and \
                        (color == 'R' or color == 'G') \
                        and (drawing[nx][ny] == 'R' or drawing[nx][ny] == 'G'):
                    visited[nx][ny][1] = True
                    queue.append((nx, ny, 1))

                    if flag == 0:
                        flag = 1

    return flag


count = 0
rg_count = 0
for i in range(N):
    for j in range(N):
        if not visited[i][j][0]:
            count += 1
            if not visited[i][j][1]:
                rg_count += 1

            bfs(i, j, drawing[i][j], drawing, visited, N)

print(count)
print(rg_count)