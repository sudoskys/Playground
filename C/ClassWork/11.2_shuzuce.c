//@11.5 课堂作业啊
#include <stdio.h>

int test3()
{
    int input;
    int num[6] = {0};
    scanf("%d", &input);
    int i;
    for (i = 0; input != 0; ++i)
    {
        num[i] = input % 2;
        input = input / 2;
    }
    for (int i = 5; i >= 0; i--)
    {
        printf("%d", num[i]);
    }
}

int main(void)
{
    test3();
}

int test2(void)
{
    double num[10] = {};
    double deal[10] = {};
    char ch;
    int i = 0;
    do
    {
        scanf("%lf", &num[i]);
        i++;
    } while ((ch = getchar()) != '\n');
    int start = -1;
    for (int i = 0; i < 9; i++)
    {
        if (num[i] > num[9])
        {
            // 一次性状态机
            if (start == -1)
            {
                deal[i] = num[9];
                start = i;
            }
            deal[i + 1] = num[i];
        }
        else
        {
            deal[i] = num[i];
        }
    }
    for (int i = 0; i < sizeof(deal) / sizeof(deal[0]); i++)
    {
        printf("%.lf ", deal[i]);
    }
}

int test1(void)
{
    double num[10] = {};
    char ch;
    int i = 0;
    do
    {
        scanf("%lf", &num[i]);
        i++;
    } while ((ch = getchar()) != '\n');
    double reverse[10] = {};
    for (int i = sizeof(num) / sizeof(num[0]) - 1; i >= 0; --i)
    {
        reverse[sizeof(num) / sizeof(num[0]) - i - 1] = num[i];
    }
    for (int i = 0; i < 10; i++)
    {
        printf("%.lf ", reverse[i]);
    }
}