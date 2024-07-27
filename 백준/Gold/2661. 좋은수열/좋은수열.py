def is_good(seq):
    length = len(seq)
    for i in range(1, length // 2 + 1):  # 정수 나눗셈
        if seq[-i:] == seq[-2 * i:-i]:
            return False
    return True


def find(n):
    if n == 1:
        return '1'

    def backtrack(seq):
        if len(seq) == n:
            return seq

        for i in '123':
            new_seq = seq + i
            if is_good(new_seq):
                result = backtrack(new_seq)
                if result:
                    return result

        return None

    return backtrack('')


N = int(input())
print(find(N))