import heapq

def min_physical_exertion():
    n = int(input().strip())
    weights = list(map(int, input().strip().split()))
    
    heapq.heapify(weights)  # 将果子数目作为最小堆
    total_exertion = 0
    
    while len(weights) > 1:
        # 取出最小的两堆进行合并
        first = heapq.heappop(weights)
        second = heapq.heappop(weights)
        
        # 计算合并这两堆的体力消耗
        new_weight = first + second
        total_exertion += new_weight
        
        # 将合并后的结果放回堆中
        heapq.heappush(weights, new_weight)
    
    print(total_exertion)

min_physical_exertion()