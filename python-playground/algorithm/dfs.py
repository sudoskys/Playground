"""
设S是有n（n≤20）个元素的集合，S的幂集是S所有可能的子集组成的集合。例如，S={a,b,c},则S的幂集={()(c)(b)(bc)(a)(ac)(ab)(abc)}。
写一个递归程序，以S为输入，输出S的幂集。即输入n（n≤20）的值以及S的n个元素，输出S的幂集。
输入样例
3
abc
输出样例
()(c)(b)(bc)(a)(ac)(ab)(abc)
"""

def generate_power_set(S, n):
    power_set = []
    subset = []

    def backtrack(index):
        if index == n:
            power_set.append("".join(subset))
            return
        # 不选S[index]
        backtrack(index + 1)
        # 选S[index]
        subset.append(S[index])
        backtrack(index + 1)
        subset.pop()

    backtrack(0)
    return power_set

if __name__ == "__main__":
    n = int(input("输入n："))
    elements = input("输入元素：")

    # 确保只取前 n 个元素
    elements = elements[:n]

    # 生成并输出幂集
    power_set = generate_power_set(elements, n)

    # 将每个子集合用括号包裹，并用""连接
    result = "(" + ")(".join(power_set) + ")"
    print(result)
