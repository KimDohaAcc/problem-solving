import sys

N = int(sys.stdin.readline())
paint = [[0, 0, 0]] + [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
MAXIMUM = 10000001
RED = 0
GREEN = 1
BLUE = 2
colors = [RED, GREEN, BLUE]
# 빨강 초록 파랑
# 비용의 최솟값
# dp[][]

dp = [[0] * (N + 1) for _ in range(3)]
dp[RED][1] = paint[1][RED]
dp[GREEN][1] = paint[1][GREEN]
dp[BLUE][1] = paint[1][BLUE]

for i in range(2, N + 1):
    for color in colors:
        color_1 = color + 1 if color + 1 < 3 else color - 2
        color_2 = color + 2 if color + 2 < 3 else color - 1
        dp[color][i] = min(dp[color_1][i - 1], dp[color_2][i - 1]) + paint[i][color]

print(min(dp[RED][N], dp[GREEN][N], dp[BLUE][N]))