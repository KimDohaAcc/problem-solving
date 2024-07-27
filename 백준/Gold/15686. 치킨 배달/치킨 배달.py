N, M = list(map(int, input().split()))
houses = []
chicken_store = []
min_dis = 100 * 13

for i in range(N):
    temp = list(map(int, input().split()))
    for j in range(N):
        if temp[j] == 1:
            houses.append([i, j])
        elif temp[j] == 2:
            chicken_store.append([i, j])


def calculate_dis():
    return [[abs(h[0] - c[0]) + abs(h[1] - c[1]) for c in chicken_store] for h in houses]


def chicken_far(dis, stores):
    global min_dis
    min_dis = min(min_dis, sum(min(dis[i][j] for j in stores) for i in range(len(houses))))


def select(idx, dis, length, stores):
    if len(stores) == M:
        chicken_far(res, stores)
        return

    for i in range(idx, length):
        stores.append(i)
        select(i + 1, dis, length, stores)
        stores.pop()


res = calculate_dis()
select(0, res, len(chicken_store), [])
print(min_dis)