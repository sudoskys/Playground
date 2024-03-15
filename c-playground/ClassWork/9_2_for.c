//@for
#include <stdio.h>

int test3()
{
    double _road=0,high=100;
    // 初始截去，以上升和下降为一个阶梯
    _road+=high;
    // 这里第几次起跳，也是上一次的落地，所以只需要九,1是被忽略的第一次
    for (int i=1;i<=9;i++){
        // jump
        high=high/2;
        _road+=high*2;
    }
    printf("%lf米 %lf米",_road,high/2);
    return 0;
}
int main(void)
{
    test3();
    return 0;
}

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
int test2()
{
    double sum = 0;
    int target;
    scanf("%d", &target);
    for (int i = 1; i <= target; i++)
    {
        sum += xx(i);
    }
    printf("%.lf", sum);
    return 0;
}

int test1()
{
    int uo = 1, sum = 0;
    for (int i = 1; i <= 100; i++)
    {
        sum += (i * 2 - 1) * uo;
        uo *= -1;
    }
    printf("%d", sum);
    return 0;
}

int test()
{
    return 0;
}