

// 8 ver
// 有 n 名学生，每名学生考m门课程，要求输出有课程成绩低于60分学生的所有成绩，在主函数完成输入。在被调函数实现输出。

#include <stdio.h>

int main()
{
    int plus(int, int);
    int minus(int, int);
    int multiply(int, int);
    int res(int x, int y, int (*p)(int, int));
    int a, b, (*p)(int, int);
    char f;
    scanf("%c,%d,%d", &f, &a, &b);
    if (f == '+')
        res(a, b, plus);
    if (f == '-')
        res(a, b, minus);
    if (f == '*')
        res(a, b, multiply);
}
int res(int x, int y, int (*p)(int, int))
{
    int result;
    result = (*p)(x, y);
    printf("%d", result);
}

int plus(int x, int y)
{
    return x + y;
}

int minus(int x, int y)
{
    return x - y;
}

int multiply(int x, int y)
{
    return x * y;
}

#include <stdio.h>

int main()
{
    int *seq(int *p, int n);
    int B[8] = {1, 0, 3, 2, 4, 0, 5, 0};
    int *p, i;
    p = B;
    seq(p, 8);
    for (i = 0; i < 8; i++)
    {
        printf("%d ", *(p + i));
    }
}

int *seq(int *p, int n)
{
    int i, j = 0;
    for (i = 0; i < n; i++)
    {
        if (p[i] != 0)
            p[j++] = p[i];
    }
    for (; j < n; j++)
        p[j] = 0;
    return (p);
}

#include <stdio.h>
#include <stdlib.h>
int main()
{
    void check(int *p, int n); //声明
    int *p, i, n;              //初始化
    scanf("%d", &n);           //输入
    p = (int *)malloc(n * sizeof(int));
    for (i = 0; i < n; i++)
        scanf("%d", p + i); //输出
    check(p, n);            //调用函数
}
void check(int *p, int n) //定义函数
{
    int i;
    for (i = 0; i < n; i++) //循环检查
        if (*(p + i) < 60)
            printf("%d ", *(p + i)); //输出
}

char s[5] = {'a', 'v', 'c', 'x', 'a'};




struct Student
    {
        long int num;
        char name[20];
        float gs;
        float yy;
        float c;
    };

#include<stdio.h>

int main(){
    struct Student student1,student2,student3;
    scanf("%ld %s %f %f %f",&student1.num,student1.name,&student1.gs,&student1.yy,&student1.c);
    scanf("%ld %s %f %f %f",&student2.num,student2.name,&student2.gs,&student2.yy,&student2.c);
    scanf("%ld %s %f %f %f",&student3.num,student3.name,&student3.gs,&student3.yy,&student3.c);
    printf("高等数学%.1f\n英语%.1f\nC语言%.1f\n",student2.gs,student2.yy,student2.c);
}

//设有三个候选人 每次输入一个得票的候选人的名字
//要求最后输出各人得票结果
#include <string.h>
#include <stdio.h>
struct person {
	char name[20];
	int count;
} leader[3] = { "Sun", 0, "Li", 0, "Zhang", 0, "Wang", 0 };
int main() {
	int i, j;
	char leader_name[20];
	printf("输入候选人姓名：\n");//票数10
	for (int i=0;i<3;i++)

    printf("请进行投票：\n");//票数10
	
    for (i = 1; i <= 10; i++) {
		scanf("%s", leader_name);
		for (j = 0; j < 3; j++)
			if (strcmp(leader_name, leader[j].name) == 0)//比较字符串
				leader[j].count++;//统计票数
	}
	for (i = 0; i < 3; i++)
		printf("%5s:%d\n", leader[i].name, leader[i].count);
	return 0;
}
