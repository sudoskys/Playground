def calculate_min_taxi_fare(distances):
    fares = []
    
    for distance in distances:
        min_cost = float('inf')
        
        # 只要有可能的分法（从1公里到小于总行程的公里数）
        for split in range(1, distance):
            # Calculate cost for the first leg
            cost1 = calculate_fare(split)
            # Calculate cost for the second leg
            cost2 = calculate_fare(distance - split)
            # Sum the costs and update minimum cost
            total_cost = cost1 + cost2
            if total_cost < min_cost:
                min_cost = total_cost

        # Compare with taking the journey without any split
        min_cost = min(min_cost, calculate_fare(distance))
        
        fares.append(min_cost)
    
    return fares


def calculate_fare(distance):
    # Initial cost assuming a ride without splits
    if distance <= 4:
        return 10
    elif distance <= 8:
        return 10 + (distance - 4) * 2
    else:
        return 10 + 4 * 2 + (distance - 8) * 2.4


def main():
    n = int(input().strip())
    distances = [int(input().strip()) for _ in range(n)]
    fares = calculate_min_taxi_fare(distances)
    
    for fare in fares:
        print(fare)


main()