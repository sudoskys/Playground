//@test boring
#include <stdio.h>
#include <math.h>
//
void main1()
{
    int i, n = 0;
    for (i = 2; i < 5; i++)
    {
        do
        {
            if (i % 3)
                continue;
            n++;
        } while (!i);
        n++;
    }
    printf("n=%d", n);
}
//全部素数
/*
int main2(){
    int i;
    int n = 0;
    int k = 0;//记录个数
    for(int m = 101; m <= 200; m++){
        for(i = 2; i <= sqrt(m); i++){
            if(m%i == 0){
                n++;
                break;
            }
        }
        if(i > sqrt(m)){
            k++;
            printf("%d ", m);
        }
        n = 0;
    }
}
*/

int main3()
{
    double sum = 0;
    int count = 0, item;
    char ch;
    do
    {
        scanf("%d", &item);
        if (item > 0)
        {
            sum += item;
            count++;
        }

    } while (item > 0);
    if (count > 0)
    {
        printf("n=%d,ave=%.2lf", count, sum / count);
    }
    else
    {
        printf("无有效成绩");
    }
    return 0;
}


int main4()
{
    double sum = 0;
    double  item;
    int count = 0;
    char ch;
    do
    {
        scanf("%lf", &item);
        if (item > 60)
        {
            sum += item;
            count++;
        }else{
            printf("Fail:%.2lf\n",item);
        }
    } while ((ch=getchar()!='\n'));
    if (count > 0)
    {
        printf("n=%d,ave=%.2lf", count, sum / count);
    }
    else
    {
        printf("无有效成绩");
    }
    return 0;
}