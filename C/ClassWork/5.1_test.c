//@测试运算符
//@date 22/10/4
#include <stdio.h>

int main1(){
    double _x;
    _x=2;
    printf("%lf\n",_x+3/2);
    int x,y;
    x=-8;
    y=-5;
    printf("%d\n",x%y);
    printf("%d\n",(1/2+1/3+1/4));
}

int main(){
    float input_;
    float result;
    scanf("%f",&input_);
    result=5*(input_-32)/9;
    printf("%.2f",result);
}