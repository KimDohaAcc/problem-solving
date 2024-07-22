N = int(input())
nums = []
visited = []
max_arr = []

for i in range(N):
    nums.append(int(input()))


# dfs
def dfs(start, key):
    visited.append(key)
    next = nums[key - 1]

    if next == start:
        global max_arr

        flag = True
        for i in range(len(visited)):
            if visited[i] > 0 and visited[i] in max_arr:
                flag = False
                break

        if not flag:
            if len(max_arr) < len(visited):
                max_arr = visited.copy()

        else:
            max_arr.extend(visited)

        return

    if next in visited:
        visited.clear()
        return

    dfs(start, next)


for i in range(N):
    dfs(i + 1, i + 1)

max_arr.sort()

print(len(max_arr))
for i in range(len(max_arr)):
    print(max_arr[i])