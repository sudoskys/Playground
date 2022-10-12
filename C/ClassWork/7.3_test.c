//@test
#include <stdio.h>
int test1(void){
    int week_day;
    scanf("%d",&week_day);
    // 定义表来取出下内存下标对应的字符串数据。
    // 复用了 6.7 class test
    // 基本思路是取余，特殊情况处理，然后判定
    weekday=week_day%7;
    char *week[7] = {"一","二","三","四","五","六","日"},*well;
    well=(day%7!=0)?(week[day%7-1]):(week[7-1]);
    printf("星期%s",well); 
}

int test2(){
    int year,month,_day;
    scanf("%d-%d",&year,&month);
    // 单独处理2月，其他按照模板
    // ((year%4==0&&year%100!=0)||(year%400==0))
    switch(month){
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:{
            printf("31天");
            break;
        }
        case 4:
        case 6:
        case 9:
        case 11:{
            printf("30天");
            break;
        }
        case 2:{
            _day=((year%4==0&&year%100!=0)||(year%400==0))?29:28;
            printf("%d天",_day);
            break;
        }

    }
    return 0;
}

int main(){
    test2();
    return 0;
}