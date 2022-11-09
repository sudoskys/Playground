#include <stdio.h>


double caculate(int x){
     double mother=0;
     for (int i=1;i<=x;i++){
          mother+=i;
     }
     return (1.0/mother);
}


int main()
{
    int in;
    double res, sum = 0;
    scanf("%d", &in);
    for (int i = 1; i <= in; i++)
    {
        res = caculate(i);
        sum += res;
    }
    printf("%.5lf", sum);
}

int GY(int a, int b)
{
    int i = 0, g = 0;
    int m = 0;
    m = a < b ? a : b;
    for (i = 1; i <= m; i++)
    {
        if (a % i == 0 && b % i == 0)
        {
            g = i;
        }
    }
    return g;
}
int GB(int a, int b)
{
    return (a * b) / GY(a, b);
}
int test8()
{
    int a, b, c, d;
    scanf("%d%d", &a, &b);
    c = GY(a, b);
    d = GB(a, b);
    printf("最大公约数=%d,最小公倍数=%d", c, d);
    return 0;
}

long XX(int n)
{
    int i;
    long result = 1;
    for (i = 1; i <= n; i++)
    {
        result *= i;
    }
    return result;
}

int test7()
{
    int in;
    long res, sum = 0;
    scanf("%d", &in);
    for (int i = 1; i <= in; i++)
    {
        res = XX(i);
        sum += res;
    }
    printf("%ld", sum + 1);
}

int isPal(int val)
{
    int sum = 0, in;
    in = val;
    while (val)
    {
        sum = sum * 10 + val % 10;
        val /= 10;
    }
    if (in - sum == 0)
        return 1;
    else
        return 0;
}

int test6()
{
    int in, res;
    scanf("%d", &in);
    res = isPal(in);
    if (res)
        printf("Yes");
    else
        printf("No");
}

int sum(int k)
{
    int sum = 0, time = 0;
    for (int i = k; i > 1; i--)
    {
        if (i % 13 == 0 || i % 17 == 0)
        {
            time += 1;
            if (time > 10)
                break;
            else
                sum += i;
        }
    }
    return sum;
}

int test5()
{
    int in, res;
    scanf("%d", &in);
    res = sum(in);
    printf("%d", res);
}

int isPer(int x)
{
    int sum = 0;
    for (int i = 1; i < x; i++)
    {
        if (x % i == 0)
        {
            sum += i;
        }
    }
    if (sum == x)
        return 1;
    else
        return 0;
}

int test4()
{
    int i, j;
    int res;
    for (i = 2; i <= 1000; i++)
    {
        res = isPer(i);
        if (res == 1)
        {
            printf("%d ", i);
        }
        else
        {
            continue;
        }
    }
}

int leap(int year)
{
    int res;
    res = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? 1 : 0;
    return res;
}

int test3()
{
    int var, rev;
    scanf("%d", &var);
    rev = leap(var);
    if (rev == 0)
        printf("No");
    if (rev == 1)
        printf("Yes");
}

int verse(int val)
{
    int sum = 0;
    while (val)
    {
        sum = sum * 10 + val % 10;
        val /= 10;
    }
    return sum;
}
int test2()
{
    int var, rev;
    scanf("%d", &var);
    rev = verse(var);
    printf("%d", rev);
}

int test1()
{
    int min(int x, int y, int z);
    int a, b, c, d;
    scanf("%d %d %d", &a, &b, &c);
    d = min(a, b, c);
    printf("%d", d);
    return 0;
}
int min(int x, int y, int z)
{
    int e, m;
    e = x < y ? x : y;
    m = e < z ? e : z;
    return (m);
}