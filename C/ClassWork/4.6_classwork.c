//@计算体积的作业
#include <stdio.h>
#define pai 3.1415926

// 计算表面积
float _area(double r){
    return r*r*r*pai*4;
}

//计算体积
float _volume(double r){
    return 4*r*r*pai*r/3;
    // do not use 4/3  or use...4.0/3
}

//入口
int main(void){
    double r_,result;
    scanf("%lf",&r_);
    result=_area(r_);
    printf("%.2lf,%.2lf",_area(r_),_volume(r_));
    return 0;
}