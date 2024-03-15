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
void show_order(struct Order o, int index) {
    printf("订单编号:%d\n", index);
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
            show_order(orders[i], i);
            found = 1;
        }
    }
    if (!found) {
        printf("未找到该顾客的订单!\n");
    }
}

// 删除订单
void delete_order() {
    int index;
    printf("请输入要删除的订单编号:");
    scanf("%d", &index);
    if (index < 0 || index >= num_orders) {
        printf("无效的订单编号!\n");
        return;
    }
    for (int i = index; i < num_orders - 1; i++) {
        orders[i] = orders[i+1];
    }
    num_orders--;
    save_orders();
}

// 主函数
int main() {
    load_orders();
    while (1) {
        printf("请选择操作:\n");
        printf("1.添加订单\n");
        printf("2.查询订单\n");
        printf("3.删除订单\n");
        printf("4.退出程序\n");
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
                delete_order();
                break;
            case 4:
                return 0;
            default:
                printf("无效的选择，请重新输入!\n");
        }
    }
}
