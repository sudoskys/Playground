//@赋值&逗号运算符
//@date 22/10/4

#include <stdio.h>

/*
x++是自增
先y=x，x再自增

x++是在有这个的这个算式中先使用x(整个，再自增1及x=x+1。

++x是先自己加1，再使用。
*/

int main(){
    int t,x,y;
    t/=5;
    x=11;
    y=(x++*1/3);
    printf("%d,%d",x,y);
    return 0;
}