import re

def 验证邮箱地址(email):
    # 使用正则表达式
    模式 = r"^[a-zA-Z0-9_\-]+@[a-zA-Z0-9_\-]+\.[a-zA-Z]{2,}$"
    if re.match(模式, email):
        try:
            print(f"{email}是一个有效的邮箱地址")
            # 分解出用户名和服务器名
            用户名, 服务器名 = email.split("@")
            # 打印结果
            print("邮箱的用户名是： ", 用户名)
            print("邮箱的服务器名是： ", 服务器名)
            return True
        except ValueError:
            print("分解邮箱时出现错误。")
            return False
    else:
        print("邮箱地址格式不正确。")
        return False

邮箱地址 = input("请输入邮箱地址：")
验证邮箱地址(邮箱地址)
