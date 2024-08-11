from collections import deque
def solution(n, m, x, y, r, c, k):
    min_dis = abs(x - r) + abs(y - c)
    remain = k - min_dis
    res = ""
    
    if remain < 0 or remain % 2 == 1:
        return "impossible"
    
    l_move, r_move, u_move, d_move = 0, 0, 0, 0
    
    if x > r:
        u_move += x - r
    else:
        d_move += r - x
        
    if y > c:
        l_move += y - c
    else:
        r_move += c - y
        
    print("L", l_move)
    print("R", r_move)
    print("D", d_move)
    print("U", u_move)
    print("remain : ", remain)
    print("n - (x + d_move) : ", n - (x + d_move))
    
    if remain > 0 and n - (x + d_move) > 0:
        d_move_second = remain // 2 if remain // 2 < n - (x + d_move) else n - (x + d_move)
        print("second_d ", d_move_second)
        d_move += d_move_second
        u_move += d_move_second
        
        remain -= d_move_second * 2

    res += "d" * d_move
        
    print("y - l_move : ", y - l_move)
    
    if remain > 0 and y - l_move > 1:
        l_move_second = remain // 2 if remain // 2 < y - l_move - 1 else y - l_move - 1
        
        l_move += l_move_second
        r_move += l_move_second
        
        remain -= l_move_second * 2
    
    res += "l" * l_move

    if remain > 0:
        rl_move = remain // 2
        res += "rl" * rl_move
        
    res += "r" * r_move
    res += "u" * u_move
    
    print(res)
    
    return res
    
    