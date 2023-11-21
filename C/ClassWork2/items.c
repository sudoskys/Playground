// C 语言实现学生信息管理系统
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// 学生信息结构体
typedef struct
{
    int id;
    char name[20];
} Stu;

// @brief 添加学生信息追加到文件
// @param filename 文件名
void add_stu(char *filename)
{
    Stu stu;
    FILE *fp = fopen(filename, "a");
    if (fp == NULL)
    {
        printf("文件打开失败");
        return;
    }
    printf("请输入学生学号:");
    scanf("%d", &stu.id);
    printf("请输入学生姓名:");
    scanf("%s", stu.name);
    fwrite(&stu, sizeof(Stu), 1, fp);
    fclose(fp);
}

// @brief 从文件中读取学生信息数组
// @param filename 文件名
void read_stu(char *filename)
{
    Stu stu;
    FILE *fp = fopen(filename, "r");
    if (fp == NULL)
    {
        printf("文件打开失败");
        return;
    }
    while (fread(&stu, sizeof(Stu), 1, fp) != 0)
    {
        printf("学号:%d 姓名:%s \n", stu.id, stu.name);
    }
    fclose(fp);
}

// @brief 从文件中删除学生信息
// @param filename 文件名
void del_stu(char *filename)
{
    Stu stu;
    FILE *fp = fopen(filename, "r");
    if (fp == NULL)
    {
        printf("文件打开失败");
        return;
    }
    FILE *fp2 = fopen("temp.txt", "w");
    if (fp2 == NULL)
    {
        printf("文件打开失败");
        return;
    }
    int id;
    int deleted = 0;
    printf("请输入要删除的学生学号:");
    scanf("%d", &id);
    while (fread(&stu, sizeof(Stu), 1, fp) != 0)
    {
        if (stu.id != id)
        {
            fwrite(&stu, sizeof(Stu), 1, fp2);
        }
        else
        {
            deleted = 1;
        }
    }
    if (deleted == 0)
    {
        printf("没有找到要删除的学生信息 \n");
    }
    fclose(fp);
    fclose(fp2);
    remove(filename);
    rename("temp.txt", filename);
}

// @brief 从文件中修改学生信息
// @param filename 文件名
void modify_stu(char *filename)
{
    Stu stu;
    FILE *fp = fopen(filename, "r");
    if (fp == NULL)
    {
        printf("文件打开失败");
        return;
    }
    FILE *fp2 = fopen("temp.txt", "w");
    if (fp2 == NULL)
    {
        printf("文件打开失败");
        return;
    }
    int id;
    printf("请输入要修改的学生学号:");
    scanf("%d", &id);
    while (fread(&stu, sizeof(Stu), 1, fp) != 0)
    {
        if (stu.id == id)
        {
            printf("修改后的学生学号:");
            scanf("%d", &stu.id);
            printf("修改后的学生姓名:");
            scanf("%s", stu.name);
        }
        fwrite(&stu, sizeof(Stu), 1, fp2);
    }
    fclose(fp);
    fclose(fp2);
    remove(filename);
    rename("temp.txt", filename);
}

// 添加记录
void add()
{
    printf("添加记录");
    add_stu("stu.txt");
}

// 删除记录
void del()
{
    printf("删除记录");
    del_stu("stu.txt");
}

// 修改记录
void modify()
{
    printf("修改记录");
    modify_stu("stu.txt");
}

// 查询记录
void search()
{
    printf("查询记录");
    read_stu("stu.txt");
}

// 退出系统
void exit_system()
{
    printf("退出系统");
    exit(1);
}

// 控制器
void controller(void (*p)())
{
    (*p)();
}

void run()
{
    char c;
    printf("系统菜单\n");
    printf("1.添加记录\n");
    printf("2.删除记录\n");
    printf("3.修改记录\n");
    printf("4.查询记录\n");
    printf("5.退出系统\n");
    do
    {
        printf("请输入菜单编号\n");
        // 读取用户输入和回车
        c = getchar();
        switch (c)
        {
        case '1':
            printf("添加记录\n");
            controller(add);
            break;
        case '2':
            printf("删除记录\n");
            controller(del);
            break;
        case '3':
            printf("修改记录\n");
            controller(modify);
            break;
        case '4':
            printf("查询记录\n");
            controller(search);
            break;
        case '5':
            printf("退出系统\n");
            controller(exit_system);
            break;
        default:
            printf("输入错误\n");
            break;
        }
    } while (c != '5' && c != '4' && c != '3' && c != '2' && c != '1');
}

int main(){
    // 循环调用,直到用户ctrl+c退出
    while (1)
    {
        getchar();
        run();
    }
    return 0;
}
