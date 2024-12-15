try:
    num_str = input("请输入一个数字：")
    num = float(num_str)

    if num < 10:
        print(f"该数字的平方为：{num ** 2}")
    elif num == 10:
        print(f"该数字的立方为：{num ** 3}")
    else:
        raise ValueError("发生异常：输入的数字过大")

except ValueError as e:
    if "could not convert string to float" in str(e):
        print("请输入一个有效的数字")
    else:
        print(e)
