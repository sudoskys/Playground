//@选择条件语句
#include <stdio.h>
#include <math.h>

int test(void){
    float a,b,t;
    scanf("%f,%f",&a,&b);
    if(a>b){
        t=a;
        a=b;
        b=t;
        }
    printf("%5.2f,%5.2f\n",a,b);
    return 0;
}


int test2(void){
    int x,y;
    scanf("%d,%d",&x,&y);
    if(x>y){
        x=y;
        y=x;
        }
    else{
        x++;
        y++;}
    printf("%d,%d",x,y);
    return 0;
}
int test3(){
    // 采用三次比较，三次交换
    double x,y,z,temp;
    scanf("%lf,%lf,%lf",&x,&y,&z);
    if (x>y) temp=x,x=y,y=temp;//x,y交换
    if (y>z) temp=y,y=z,z=temp;//y,z交换
    if (x>z) temp=x,x=z,z=temp;//x,z交换
    printf("%.2lf,%.2lf,%.2lf",x,y,z);
}
int test4(){
    int num;
    scanf("%d",&num);
    if (num%3==0&&num%7!=0){
        printf("YES");
    }else{
        printf("NO");
    }

    return 0;
}

int test5(void){
    char test;
    scanf("%c",&test);
    test=(test<='Z'&&test>='A')?(test+32):test;
    // 炫技
    printf("%c\n",test);
    return 0;
}

int test6(void){
    //奇数偶数
    int test;
    char *tip;
    scanf("%c",&test);
    tip=(test%2==0)?("偶数"):("奇数");
    // 炫技
    printf("%s\n",tip);
    return 0;
}

int test7(void){
    //分数
    int score;
    char *tip;
    char defalut_tip[]="你输入的分数不合法";
    scanf("%d",&score);
    tip=(score>90)?("A"):(score>80)?("B"):(score>70)?("C"):(score>60)?("D"):(score>0)?("E"):(defalut_tip);
    // 炫技
    printf("%s\n",tip);
    return 0;
}
int main(void){
    test7();
    return 0;
}