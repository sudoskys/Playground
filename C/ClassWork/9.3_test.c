//@for test
#include <stdio.h>
#include <string.h>
#include <math.h>

/// @brief 测试五
/// @return 返回0
int test5()
{
    // C语言不能实现动态增容，所以我们不能使用简便的方法...
    int yue_sum = 0;
    int num;
    scanf("%d", &num);
    for (int i = 1; i < num; i++)
    {
        if (num % i == 0 && num != i)
        {
            yue_sum += i;
        }
    }
    if (yue_sum == num)
    {
        printf("%d是完数", num);
    }
    else
    {
        printf("%d不是完数", num);
    }
    return 0;
}
int main(void)
{
    test5();
    return 0;
}

int test()
{
    return 0;
}
//---------------------------------
/// @brief 递归
/// @param a
/// @param i
/// @return 返回若干
double Num_sum(int a, int i)
{
    if (i > 0)
    {
        // return Num_sum(a, i - 1) + a * pow(10, i - 1);
    }
    else
    {
        return 0;
    }
}

int test4()
{
    int a, num;
    double sum = 0;
    scanf("%d,%d", &a, &num);
    for (int i = 1; i <= num; ++i)
    {
        sum += Num_sum(a, i);
    }
    printf("a+aa+aaa+...=%.lf", sum);
    return 0;
}
//---------------------------------
int test3()
{
    for (int i = 1000; i < 10000; i++)
    {
        int tmp;
        tmp = i % 100 + i / 100;
        if (tmp * tmp == i)
        {
            printf("%d ", i);
        }
    }
    return 0;
}
//---------------------------------

double add_add(int num)
{
    double sum = 0;
    for (int i = 1; i <= num; i++)
    {
        sum += i;
    }
    return sum;
}
int test2()
{
    double sum = 0, num;
    scanf("%lf", &num);
    if (num > 0)
    {
        for (int i = 1; i <= num; i++)
        {
            sum += add_add(i);
        }
        printf("%.lf", sum);
    }
    else
    {
        printf("Error");
    }
    return 0;
}

//-----------------------------------

/*
input
*/
double input()
{
    double target;
    scanf("%lf", &target);
    return target;
}
/*
for test ,!x
*/
double xx(int item)
{
    int index = 1;
    double result = 1.0;
    while (index <= item)
    {
        result *= index;
        index++;
    }
    return result;
}
int test1()
{
    double sum = 1, num;
    scanf("%lf", &num);
    for (int i = 1; i <= num; i++)
    {
        // caculate and sum
        sum += 1 / xx(i);
    }
    printf("%lf", sum);
    return 0;
}
