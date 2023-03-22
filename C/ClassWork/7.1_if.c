//@7.1测试
#include <stdio.h>
#include <string.h>

int test1()
{
    int x = 123, a;
    scanf("%d", &a);
    if (a != 123)
    {
        if (a > 123)
            printf("Wrong,Too high");
        else
            printf("Wrong,Too low");
    }
    else
    {
        printf("Right");
    }
    return 0;
}

int test2()
{
    char x;
    scanf("%c", &x);
    if (x >= 48 && x <= 57 || x >= 97 && x <= 122 || x >= 65 && x <= 90)
    {
        if (x >= 97 && x <= 122 || x >= 65 && x <= 90)
        {
            printf("字母");
        }
        else
        {
            printf("数字");
        }
    }
    else
    {
        printf("其他字符");
    }
}

int test3()
{
    double x, bouns;
    scanf("%lf", &x);
    if (x <= 100000)
        bouns = x * 0.1;
    if (x > 100000 && x <= 200000)
        bouns = 100000 * 0.1 + (x - 100000) * 0.075;
    if (x > 200000 && x <= 400000)
        bouns = 100000 * 0.1 + 100000 * 0.075 + (x - 200000) * 0.05;
    if (x > 400000 && x <= 600000)
        bouns = 100000 * 0.1 + 100000 * 0.075 + 200000 * 0.05 + (x - 400000) * 0.03;
    if (x > 600000 && x <= 1000000)
        bouns = 100000 * 0.1 + 100000 * 0.075 + 200000 * 0.05 + 200000 * 0.03 + (x - 600000) * 0.015;
    if (x > 1000000)
        bouns = 100000 * 0.1 + 100000 * 0.075 + 200000 * 0.05 + 200000 * 0.03 + 400000 * 0.015 + (x - 1000000) * 0.01;
    printf("提成为：bouns=%.6lf", bouns);
}

int rev_int(int tar)
{
    int r = 0;
    while (tar != 0)
    {
        r = r * 10 + tar % 10;
        tar = tar / 10;
    }
    return r;
}
int chai(num)
{
    int a = 0, i=0, l;
    char a[30];
    a = num;
    l = strlen(a);
    for (i = 0; i < l; i++)
    {
        printf("%d ", a[i] - '0');
    }
    printf("\n");
}

int main(void)
{
    int _n, n, m = 1, r;
    scanf("%d", &n);
    _n = rev_int(n);
    r = n;
    while (n / 10)
    {
        m += 1;
        n = n / 10;
    }
    chai(_n);
    if (m < 5 && r > 0)
    {
        printf("输入的数值为%d位数，逆序为%d\n", m, _n);
    }
    else
    {
        printf("输入有误");
    }

    return 0;
}