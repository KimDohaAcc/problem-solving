dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
drawing = []
maxValue = 0


# bfs 함수
def bfs(x, y, paint):
    queue = [[x, y]]
    drawing[x][y] = paint
    cnt = 0

    while len(queue) > 0:
        node = queue.pop()
        cnt += 1

        for i in range(4):
            nx = node[0] + dx[i]
            ny = node[1] + dy[i]

            if nx < 0 or ny < 0 or nx >= n or ny >= m:
                continue

            if drawing[nx][ny] == 1:
                drawing[nx][ny] = paint
                queue.append([nx, ny])

    global maxValue
    if maxValue < cnt:
        maxValue = cnt


# 입력
arr = input().split(' ')
n = int(arr[0])
m = int(arr[1])

for i in range(n):
    drawing.append(list(map(int, input().split(' '))))

# 큐를 돌기
color = 2  # 그림에 색을 칠해서 구별하자

for i in range(n):
    for j in range(m):
        if drawing[i][j] == 1:
            bfs(i, j, color)
            color += 1

print(color - 2)
print(maxValue)