def str_to_sec(time_str):
    h, m, s = map(int, time_str.split(':'))
    return h * 3600 + m * 60 + s

def sec_to_str(seconds):
    h, r = divmod(seconds, 3600)
    m, s = divmod(r, 60)
    return f"{h:02d}:{m:02d}:{s:02d}"

def solution(play_time, adv_time, logs):
    play_sec = str_to_sec(play_time)
    adv_sec = str_to_sec(adv_time)
    
    total_time = [0] * (play_sec + 1)
    
    for log in logs:
        start, end = map(str_to_sec, log.split('-'))
        total_time[start] += 1
        total_time[end] -= 1
    
    for i in range(1, len(total_time)):
        total_time[i] += total_time[i-1]
    
    for i in range(1, len(total_time)):
        total_time[i] += total_time[i-1]
    
    max_time = 0
    max_start = 0
    
    for i in range(play_sec - adv_sec + 1):
        if i == 0:
            time = total_time[adv_sec - 1]
        else:
            time = total_time[i + adv_sec - 1] - total_time[i - 1]
        
        if time > max_time:
            max_time = time
            max_start = i
    
    return sec_to_str(max_start)