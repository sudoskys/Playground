# 利用摩耳投票算法（Boyer-Moore Voting Algorithm）。这个算法能在 O(n) 时间复杂度内判断是否存在多数元素。
def find_majority_element(arr):
    candidate = None
    count = 0
    
    # 遍历数组中的每一个元素
    for num in arr:
        if count == 0:
            candidate = num
        # 更新候选人及其计数
        count += 1 if num == candidate else -1

    # 计算候选人在数组中的出现次数
    if arr.count(candidate) > len(arr) // 2:
        return candidate, arr.count(candidate)
    else:
        return "no"

# 读取输入
n = int(input("请输入数组长度n: "))
arr = list(map(int, input("请输入数组元素，用空格分隔: ").split()))

# 调用函数并打印结果
result = find_majority_element(arr)
if result == "no":
    # 如果不存在多数元素，输出 "no"
    print("no")
else:
    # 如果存在多数元素，输出该元素及其出现次数
    print(result[0])
    print(result[1])
