import sys

N, M = list(map(int, sys.stdin.readline().split()))

numbers = [0] + list(map(int, sys.stdin.readline().split()))
num_range = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]

sum = [0] * (N + 1)
for i in range(1, N + 1):
    sum[i] = sum[i - 1] + numbers[i]

for i in range(M):
    start = num_range[i][0] - 1
    end = num_range[i][1]

    print(sum[end] - sum[start])