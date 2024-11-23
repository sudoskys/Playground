
# 判断是否闰年
def is_leap_year(year):
    if year % 4 == 0 and year % 100 != 0 or year % 400 == 0:
        return True
    return False
import random



number = random.randint(10000, 99999)

print(f"随机产生的五位正整数为： {number}")



digits = [int(d) for d in str(number)]

print(f"万位是： {digits[0]} ，千位是： {digits[1]} ，百位是： {digits[2]} ，十位是： {digits[3]} ，个位是： {digits[4]}\n")



for i in range(len(digits)):
    digits[i] = (digits[i] + 3) % 8



print(f"经过每位数字都加上3，然后用和除以8的余数代替该数字操作后:")

print(f"万位是： {digits[0]} ，千位是： {digits[1]} ，百位是： {digits[2]} ，十位是： {digits[3]} ，个位是： {digits[4]}\n")



digits[0], digits[4] = digits[4], digits[0]

print(f"经过将第一位和第五位交换数字操作后:")

print(f"万位是： {digits[0]} ，千位是： {digits[1]} ，百位是： {digits[2]} ，十位是： {digits[3]} ，个位是： {digits[4]}\n")



digits[1], digits[3] = digits[3], digits[1]

print(f"经过将第二位和第四位交换数字操作后:")

print(f"万位是： {digits[0]} ，千位是： {digits[1]} ，百位是： {digits[2]} ，十位是： {digits[3]} ，个位是： {digits[4]}\n")



final_password = ' '.join(map(str, digits))

print(f"经过加密，得到的密码为：{final_password}")




if __name__ == '__main__':
    year = int(input('请输入年份：'))
    if is_leap_year(year):
        print('%d年是闰年' % year)
    else:
        print('%d年不是闰年' % year)