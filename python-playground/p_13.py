# 从汇率兑换.txt文件读取数据生成字典
exchange_rates = {}

with open('汇率兑换.txt', 'r', encoding='utf-8') as file:
    for line in file:
        # 找出括号的位置，解析出货币代码和汇率
        if '(' in line and ')' in line:
            start = line.find('(')
            end = line.find(')')
            currency_code = line[start+1:end]
            rate_str = line.split()[-2]
            # 将汇率转换为浮点数并存入字典
            exchange_rates[currency_code] = float(rate_str)

while True:
    user_input = input("请输入货币和金额(例如HKD1000)，输入0退出程序：").strip()

    if user_input == '0':
        break

    # 检查输入长度和是否有数字部分
    if len(user_input) < 4 or not any(char.isdigit() for char in user_input):
        print("数字错误")
        continue

    # 分离货币代码和金额
    currency_code = ''.join(filter(str.isalpha, user_input)).upper()
    amount_str = ''.join(filter(str.isdigit, user_input))

    if currency_code not in exchange_rates:
        print("外币代码错误")
        continue

    try:
        amount = float(amount_str)
    except ValueError:
        print("数字错误")
        continue

    # 进行汇率转换
    conversion_rate = exchange_rates[currency_code]
    converted_amount = amount * conversion_rate

    print(f"转换后的人民币金额：人民币{converted_amount:.2f}")
