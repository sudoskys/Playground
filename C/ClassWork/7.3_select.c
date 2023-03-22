//@7.3选择结构综合运用

#include <stdio.h>

int test1(){
    int year,tip;
    scanf("%d",&year);
    if ((year%4==0&&year%100!=0)||(year%400==0)){
        printf("%d is a leap year",year);
    }else{
        printf("%d is not a leap year",year);
    }
}

int code(){
    double a,b,c;
    double derta;
    double res1,res2;
    scanf("%lf,%lf,%lf",&a,&b,&c);
    derta=b*b-4*a*c;
    if (derta<0){
        printf("方程没有实数根");
    }else{
        res1=(0-b+sqrt(derta))/(2*a);
        res2=(0-b-sqrt(derta))/(2*a);
        if (res2==res1){
            printf("方程有一个实数根 %.4lf",res1);
        }else{
            printf("方程有两个实数根，%.4lf，%.4lf",res1,res2);
        }
        
    }
    return 0;
}

int main(){
    code();
}




