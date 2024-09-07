N = int(input())
INF = 100001
dp = [[INF, [i]] for i in range(N + 1)]

dp[1][0] = 1

for i in range(1, N + 1):
    if dp[i][0] == INF:
        continue

    if i * 3 <= N and (dp[i][0] + 1) < dp[i * 3][0]:
        dp[i * 3][0] = dp[i][0] + 1
        dp[i * 3][1] = dp[i][1].copy()
        dp[i * 3][1].append(i * 3)

    if i * 2 <= N and (dp[i][0] + 1) < dp[i * 2][0]:
        dp[i * 2][0] = dp[i][0] + 1
        dp[i * 2][1] = dp[i][1].copy()
        dp[i * 2][1].append(i * 2)

    if (i + 1) <= N and (dp[i][0] + 1) < dp[i + 1][0]:
        dp[i + 1][0] = dp[i][0] + 1
        dp[i + 1][1] = dp[i][1].copy()
        dp[i + 1][1].append(i + 1)

print(len(dp[N][1]) - 1)
for i in reversed(dp[N][1]):
    print(i, end=' ')