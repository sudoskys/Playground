import re

# print({:^4.2f}.format(3.1415926))

print("{}".format(3.1415926))

t_1="test-all".split("-")
print(t_1[0]+"+"+t_1[-1])

t_2="test-all-123".replace("-","")
# 统计输入的字母数字和其他字符的个数
print(f"number:{sum([1 for i in t_2 if i.isdigit()])}")
print(f"alpha:{sum([1 for i in t_2 if i.isalpha()])}")
print(f"other:{sum([1 for i in t_2 if not i.isalnum()])}")

test=input()
# 输出其中最长的单词
print(max(re.findall(r'\b\w+\b',test),key=len))

"""
平方根格式化

获得用户输入的一个整数a，计算a的平方根（可使用pow(x,y)实现），保留小数点后3位，并打印输出。

输出结果采用宽度30个字符、右对齐输出、多余字符采用加号(+)填充。

如果结果超过30个字符，则以结果宽度为准。
"""

a=int(input())
print("{:+>30.3f}".format(pow(a,0.5)))
