//@while
#include <stdio.h>

int test1(){
    int c;
    while ((c=getchar())!="\n")
        switch (c-'2')
        {
        case 0:
        case 1:putchar(c+4);
        case 2:putchar(c+4);break;
        case 3:putchar(c+3);
        default:putchar(c+2);break;
        }
}

int test2(){
    int sum=0,index=1;
    while(index<=99){
        sum+=index;
        index++;
        index++;
    }
    printf("%d",sum);
    return 0;
}

int test3(){
    double at=1,index=1;
    while(index<=15){
        at*=index;
        index++;
    }
    printf("%.lf",at);
    return 0;
}

int test4(){
    //采用了奇怪的方法
    int index=1,appecpt=0;
    double num,sum=0;
    scanf("%d",&appecpt);
    while (index<=appecpt)
    {
        scanf("%lf",&num);
        sum+=num;
        index++;
    }
    printf("%.2lf",(sum/appecpt));
    return 0;
}


int main(void){
    test4();
}