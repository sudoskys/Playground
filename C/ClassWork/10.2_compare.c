//@test
#include <stdio.h>

int test1()
{
    int peach=1;
    for (int i=0;i<9;++i)
    {
        // 昨天的桃子
        peach+=1;
        // 成倍
        peach*=2;
    }
    printf("total=%d",peach);
    return 0;
}

int main(void)
{
    test1();
    return 0;
}

int test()
{
    return 0;
}