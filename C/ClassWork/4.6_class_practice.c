//@课堂作业
#include <stdio.h>


int work1(void){
    int day,mo,year;
    scanf("%d/%d/%d",&mo,&day,&year);
    printf("%d-%02d-%02d",year,mo,day);
    return 0;
}

int work2(){
    int one,two,three;
    scanf("(%d)%d-%d",&one,&two,&three);
    printf("%d,%d,%d",one,two,three);
    return 0;
}


int main(){
    work2();
}