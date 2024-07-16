P = int(input())
res = []
for t in range(P):
    # arr = input().split(' ') # 문자로 들어간 상황
    arr = list(map(int, input().split())) # int로 변환
    count = 0
    for i in range(2, len(arr)):
        for j in range(1, i):
            if arr[i] < arr[j]:
                temp = arr[i]
                arr.pop(i)
                arr.insert(j, temp)
                count += i - j
                break

    # print(t + 1, count)
    res.append(f"{t+1} {count}\n")

print(''.join(res))