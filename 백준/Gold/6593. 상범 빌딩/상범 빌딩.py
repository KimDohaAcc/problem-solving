import sys
from collections import deque

dx = (-1, 1, 0, 0, 0, 0)
dy = (0, 0, 1, -1, 0, 0)
dz = (0, 0, 0, 0, 1, -1)

input = sys.stdin.readline

while True:
    L, R, C = map(int, input().split())

    if L == 0 and R == 0 and C == 0:
        break

    box = list()
    start_position = tuple()

    for i in range(L):
        outer_list = []
        for j in range(R+1):
            inner_list = list(input().strip('\n'))
            if not inner_list:
                continue

            for k in range(C):
                if inner_list[k] == 'S':
                    start_position = (0, i, j, k)
            outer_list.append(inner_list)
        box.append(outer_list)
    #
    # for i in range(L):
    #     print(f'floor {i+1}')
    #     for j in range(R):
    #         print(box[i][j], end='\n')

    queue = deque()
    queue.append(start_position)
    visited = [[[False] * C for _ in range(R)] for _ in range(L)]
    flag = False
    cnt, sx, sy, sz = start_position
    visited[sx][sy][sz] = True

    while queue:
        count, x, y, z = queue.popleft()
        if box[x][y][z] == 'E':
            print(f'Escaped in {count} minute(s).')
            flag = True
            break

        for i in range(6):
            nx, ny, nz = x + dx[i], y + dy[i], z + dz[i]

            if nx < 0 or ny < 0 or nz < 0 or nx >= L or ny >= R or nz >= C:
                continue

            if visited[nx][ny][nz]:
                continue

            if box[nx][ny][nz] == '#':
                continue

            queue.append((count + 1, nx, ny, nz))
            visited[nx][ny][nz] = True

    if not flag:
        print('Trapped!')