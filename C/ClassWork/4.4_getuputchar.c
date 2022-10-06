//@getchar相关测试
#include <stdio.h>

int main1(void){
    char now,_P,_F;
    now=getchar();
    _P=now-1;
    _F=now+1;
    printf("%d,%c,%c",now,_P,_F);
    return 0;
}

int main2(void){
    char big,small;
    big=getchar();
    small=big+32;
    putchar(small);
    return 0;
}