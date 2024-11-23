
goodses = [["手机", "华为", 6800, 50], ["电脑", "联想", 9999, 110], ["冰箱", "海尔", 3899, 60]]

def add_goods():
    name = input("请输入商品名称：")
    brand = input("请输入商品品牌：")
    price = int(input("请输入商品价格："))
    quantity = int(input("请输入商品数量："))
    goodses.append([name, brand, price, quantity])
    print("\n商品添加完成，商品列表：")
    display_goods()

def display_goods():
    print("\n现在商品库存列表：")
    for goods in goodses:
        print(f"{goods[0]}\t-\t{goods[1]}\t-\t{goods[2]}\t-\t{goods[3]}")

def delete_goods():
    name = input("请输入要删除商品的名称：")
    global goodses
    goodses = [goods for goods in goodses if goods[0] != name]
    print("\n商品删除完成，剩余商品库存列表：")
    display_goods()

def main():
    while True:
        print("\n欢迎使用库存管理系统")
        print("1. 商品入库")
        print("2. 商品显示")
        print("3. 删除商品")
        print("4. 退出系统")
        option = input("请选择要进行的操作（输入操作前的序号）：")

        if option == '1':
            add_goods()
        elif option == '2':
            display_goods()
        elif option == '3':
            delete_goods()
        elif option == '4':
            print("\n程序已成功退出！")
            break
        else:
            print("\n无效的选项，请重新输入！")
main()



