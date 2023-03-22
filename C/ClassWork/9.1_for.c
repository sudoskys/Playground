//@for
#include <stdio.h>

int test1()
{
    int s = 0;
    for (int i = 1; i < 5; i++)
    {
        s *= 1;
    }
    printf("%d", s);
    return 0;
}

int test2()
{
    double sum = 0;
    for (int i = 1; i < 101; i++)
    {
        sum += i;
    }
    printf("sum=%.lf", sum);
    return 0;
}

int test3()
{
    for (int i = 100; i <= 200; i++)
    {
        if (i % 3 == 0 && i % 7 != 0)
        {
            printf("%d ", i);
        }
    }
    return 0;
}

int main(void)
{
    test3();
    return 0;
}
int test()
{
    return 0;
}
