//@5.8 classwork 
#include <stdio.h>
#define pai 3.14

int test(){
    double x=2.5,y=4.7;
    int a=7;
    double res;
    res=x+a%3*(int)(x+y)%2/4;
    printf("%lf",res);
    int i,j,k;
    printf("%d",(i=4,j=16,k=32));
} 


// 题目需要，写死此函数且不抽离逻辑接口，采用一刀流 
int money_caculate(){
    int money;
    scanf("%d",&money); 
    int _20,_10,_5,_1;
    _20=money/20;
    money=(money-_20*20);
    _10=money/10;
    money=(money-_10*10);
    _5=money/5;
    money=(money-_5*5);
    _1=money;
    printf("%d,%d,%d,%d",_20,_10,_5,_1);
} 

double caculate_V_round(double _r)
{
    double _v;
    _v=(4.0/3*pai*_r*_r*_r);
    return _v;
}


double caculate_S_round(double _r)
{
    double _s;
    _s=(pai*_r*_r);
    return _s;
}

int main(){
    double _r,_v,_s;
    scanf("%lf",&_r);
    _v=caculate_V_round(_r);
    _s=caculate_S_round(_r);
    printf("圆面积=%.2lf,圆球体积=%.2lf",_s,_v);
}