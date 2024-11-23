import heapq
import sys

def dijkstra(n, start, graph):
    # 用来记录从起点到各个点的最短距离
    distances = [sys.maxsize] * (n + 1)
    distances[start] = 0
    priority_queue = [(0, start)]

    while priority_queue:
        current_distance, current_node = heapq.heappop(priority_queue)

        # 如果已知的最小距离小于当前距离，跳过此节点
        if current_distance > distances[current_node]:
            continue

        for neighbor, weight in graph[current_node]:
            distance = current_distance + weight
            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(priority_queue, (distance, neighbor))

    return distances

def main():
    inp = input().split()
    n = int(inp[0])  # 节点数
    m = int(inp[1])  # 边数
    x = int(inp[2])  # 目的地节点

    graph = [[] for _ in range(n + 1)]
    reverse_graph = [[] for _ in range(n + 1)]

    for _ in range(m):
        u, v, w = map(int, input().split())
        graph[u].append((v, w))
        reverse_graph[v].append((u, w))

    # 从所有点到x的最短路径
    shortest_to_x = dijkstra(n, x, reverse_graph)
    # 从x到所有点的最短路径
    shortest_from_x = dijkstra(n, x, graph)

    # 计算所有点到x再返回的最短时间的最大值
    max_round_trip_time = 0
    for i in range(1, n + 1):
        if shortest_to_x[i] < sys.maxsize and shortest_from_x[i] < sys.maxsize:
            round_trip_time = shortest_to_x[i] + shortest_from_x[i]
            max_round_trip_time = max(max_round_trip_time, round_trip_time)

    print(max_round_trip_time)

main()