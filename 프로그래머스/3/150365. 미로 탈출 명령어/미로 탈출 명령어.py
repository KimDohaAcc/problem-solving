from collections import deque
def solution(n, m, x, y, r, c, k):
    ver = abs(x - r)
    hor = abs(y - c)
    dis = ver + hor
    remain = k - dis

    if dis > k or remain % 2 != 0:
        return "impossible"

    moves = ""

    d_space = max(0, r - x)
    l_space = max(0, y - c)
    r_space = max(0, c - y)
    u_space = max(0, x - r)

    d_move = remain // 2 if remain // 2 < (n - x - d_space) else (n - x - d_space)
    moves += "d" * (d_move + d_space)
    remain -= d_move * 2
    
    l_move = remain // 2 if remain // 2 < (y - l_space - 1) else (y - l_space - 1)
    moves += "l" * (l_move + l_space)
    remain -= l_move * 2
    
    # r 다음에 l이 와야 사전 순 더 빠름 제발 ㅠㅠㅠ
    if m >= 2:
        moves += "rl" * (remain // 2)
    moves += "r" * (r_space + l_move)
    moves += "u" * (u_space + d_move)
    
    print(d_move)
    print(l_move)


    return moves