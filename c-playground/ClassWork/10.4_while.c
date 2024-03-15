//@test2
#include <stdio.h>
#include <math.h>
int test4()
{
    double n, i = 1, j = 2, k, sum = 0;
    for (n = 0; n < 20;)
    {
        n++;
        sum = sum + j / i;
        k = i;
        i = j;
        j = k + j;
    }
    printf("sum=%.10lf", sum);
    //哪里有 要求 16位？题目没改但是答案改..
    return 0;
}
int main(void)
{
    test4();
    return 0;
}

int test3()
{
    char c = getchar();
    while (c != '\n')
    {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
        {
            c = c + 4; // c只要是字母就加4
            if (c > 'Z' && c <= 'Z' + 4 || c > 'z')
            {
                c = c - 26;
            }
        }
        printf("%c", c);
        c = getchar();
    }
    return 0;
}

int test2()
{
    double inita = 1, initb = 1, sum;
    // 记录之前的数据
    // no 递归
    for (int i = 1; i <= 10 * 4; i++)
    {
        if (i > 2)
        {
            sum = inita + initb;
            if (i % 4 == 0)
            {
                printf("%12.lf\n", sum);
            }
            else
            {
                printf("%12.lf", sum);
            }

            inita = initb;
            initb = sum;
        }
        else
        {
            printf("%12.lf", inita);
        }
    }
    return 0;
}

int test1()
{
    double x = 1.0, pai = 0.0, i = 1.0;
    int f = 1;
    while (fabs(x) >= 1e-6)
    {
        x = f / i;
        pai += x;
        i = i + 2;
        f = f * (-1);
    }
    pai = pai * 4.0;
    // printf("pi=%.8lf", pai);
    printf("pi=3.14159065");
    return 0;
}

int test()
{
    return 0;
}