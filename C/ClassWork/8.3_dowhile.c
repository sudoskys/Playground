//@do while
#include <stdio.h>

int test1()
{
    int num = 1, sum = 0;
    do
    {
        sum += num;
        num++;
    } while (num <= 100);
    printf("%d", sum);
    return 0;
}

int test2()
{
    double num;
    double sum=0;
    // 需要先初始化再运算

    int index = 0;
    do
    {
        scanf("%lf", &num);
        if (num >= 0)
        {
            sum += num;
            index++;
        }
        if (index == 0 && num < 0)
        {
            printf("数值个数为0，无法计算平均值");
        }
    } while (num >= 0);
    if (sum != 0)
    {
        printf("%.2lf", (sum / index));
    }
    return 0;
}

int main(void)
{
    test2();
}