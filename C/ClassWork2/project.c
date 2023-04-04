#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ORDERS 100 // 最大订单数量
#define MAX_NAME_LEN 20 // 姓名最大长度
#define MAX_DISHES 10 // 最多菜品数量

struct Order {
    char name[MAX_NAME_LEN];
    int table_number;
    int num_dishes;
    char dishes[MAX_DISHES][MAX_NAME_LEN];
    float total_price;
};

struct Order orders[MAX_ORDERS];
int num_orders = 0;

// 加载订单数据
void load_orders() {
    FILE *fp = fopen("orders.dat", "rb");
    if (fp != NULL) {
        fread(&num_orders, sizeof(num_orders), 1, fp);
        fread(orders, sizeof(struct Order), num_orders, fp);
        fclose(fp);
    }
}

// 保存订单数据和导出为 txt 文件
void save_orders() {
    FILE *fp_dat = fopen("orders.dat", "wb");
    FILE *fp_txt = fopen("orders.txt", "w");
    if (fp_dat != NULL && fp_txt != NULL) {
        fwrite(&num_orders, sizeof(num_orders), 1, fp_dat);
        fwrite(orders, sizeof(struct Order), num_orders, fp_dat);
        for (int i = 0; i < num_orders; i++) {
            fprintf(fp_txt, "顾客姓名:%s\n", orders[i].name);
            fprintf(fp_txt, "桌号:%d\n", orders[i].table_number);
            fprintf(fp_txt, "菜品数量:%d\n", orders[i].num_dishes);
            fprintf(fp_txt, "菜品列表:");
            for (int j = 0; j < orders[i].num_dishes; j++) {
                fprintf(fp_txt, "%s ", orders[i].dishes[j]);
            }
            fprintf(fp_txt, "\n");
            fprintf(fp_txt, "总价:%.2f\n\n", orders[i].total_price);
        }
        fclose(fp_dat);
        fclose(fp_txt);
    }
}


// 添加订单
void add_order() {
    struct Order o;
    printf("请输入顾客姓名:");
    scanf("%s", o.name);
    printf("请输入桌号:");
    scanf("%d", &o.table_number);
    printf("请输入菜品数量:");
    scanf("%d", &o.num_dishes);
    for (int i = 0; i < o.num_dishes; i++) {
        printf("请输入第%d道菜品名称:", i+1);
        scanf("%s", o.dishes[i]);
    }
    printf("请输入总价:");
    scanf("%f", &o.total_price);
    orders[num_orders++] = o;
    save_orders();
}

// 显示订单
void show_order(struct Order o) {
    printf("顾客姓名:%s\n", o.name);
    printf("桌号:%d\n", o.table_number);
    printf("菜品数量:%d\n", o.num_dishes);
    printf("菜品列表:");
    for (int i = 0; i < o.num_dishes; i++) {
        printf("%s ", o.dishes[i]);
    }
    printf("\n");
    printf("总价:%.2f\n", o.total_price);
}

// 查询订单
void query_order() {
    char name[MAX_NAME_LEN];
    printf("请输入要查询的顾客姓名:");
    scanf("%s", name);
    int found = 0;
    for (int i = 0; i < num_orders; i++) {
        if (strcmp(orders[i].name, name) == 0) {
            show_order(orders[i]);
            found = 1;
        }
    }
    if (!found) {
        printf("未找到该顾客的订单!\n");
    }
}

// 主函数
int main() {
    load_orders();
    while (1) {
        printf("请选择操作:\n");
        printf("1.添加订单\n");
        printf("2.查询订单\n");
        printf("3.退出程序\n");
        int choice;
        scanf("%d", &choice);
        switch (choice) {
            case 1:
                add_order();
                break;
            case 2:
                query_order();
                break;
            case 3:
                return 0;
            default:
                printf("无效的选择，请重新输入!\n");
        }
    }
}

/*
# 餐厅订单查询系统

## 1. 项目概述

本项目是一个简单的餐厅订单查询系统，使用C语言实现。用户可以通过该系统添加顾客订单，并且能够通过顾客姓名查询订单信息。

## 2. 实现细节

### 2.1 数据存储

为了使得数据能够持久化，程序将每个订单的信息存储在一个结构体中，并且将所有订单数据保存在一个二进制文件当中。在程序启动时，会加载文件中的订单数据，这样便可以不用担心程序关闭后数据丢失的问题。具体来说，程序使用了以下数据结构：

```c
struct Order {
    char name[MAX_NAME_LEN];
    int table_number;
    int num_dishes;
    char dishes[MAX_DISHES][MAX_NAME_LEN];
    float total_price;
};

struct Order orders[MAX_ORDERS];
int num_orders = 0;
```

其中，`MAX_ORDERS`、`MAX_NAME_LEN`和`MAX_DISHES`分别表示最大订单数量、姓名最大长度和最多菜品数量。程序将所有订单存储在`orders`数组中，`num_orders`变量记录当前已经存储的订单数量。同时，程序定义了两个函数`load_orders()`和`save_orders()`来加载和保存订单数据。具体的实现方法请参考代码部分的注释。

### 2.2 添加订单

当用户选择添加订单时，程序会提示用户输入顾客姓名、桌号、菜品数量、每个菜品的名称以及订单总价。程序将这些信息存储在一个新的`Order`结构体实例中，并将其添加到`orders`数组末尾，最后调用`save_orders()`函数保存修改后的订单数据。具体的实现方法请参考代码部分的注释。

### 2.3 查询订单

当用户选择查询订单时，程序会提示用户输入要查询的顾客姓名。程序遍历`orders`数组，找到所有姓名与要查询姓名相同的订单，并且输出它们的详细信息。如果没有找到任何匹配的订单，则输出“未找到该顾客的订单!”。具体的实现方法请参考代码部分的注释。

## 3. 总结

本项目是一个简单的餐厅订单查询系统，使用C语言实现。该系统能够添加顾客订单，并且能够通过顾客姓名查询订单信息。为了使得数据能够持久化，程序将所有订单数据保存在一个二进制文件中。
*/