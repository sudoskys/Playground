import re
from datetime import datetime

def 检查身份证(id_number):
    current_year = datetime.now().year
    pattern = (
        r"^[1-9]\d{5}"
        r"(19\d{2}|20[0-2]\d|2024)"
        r"(0[1-9]|1[0-2])"
        r"(0[1-9]|[1-2]\d|3[0-1])"
        r"\d{3}"
        r"(\d|X|x)$"
    )
    
    match = re.match(pattern, id_number)
    
    if not match:
        return "输入的身份证号码格式不符合规范。"
    
    year = int(match.group(1))
    
    if year > current_year:
        return "出生年份不能超过当前年份。"
    
    return "输入的身份证号码格式符合规范。"

# 用户输入
user_input = input("请输入身份证号码：")
print(检查身份证(user_input))
