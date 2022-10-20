//@test
#include <stdio.h>

int lan(int num){
    int rox;
    for (int i=1;i<=num;i++){
        rox=num*i;
        printf("%d*%d=%-2d ",num,i,rox);
    }
    return 0;
}
int test3(){
    // 纵列循环负责换行，横行函数负责生成内容
    for (int i=1;i<=9;i++){
        lan(i);
        printf("\n");
    }
    return 0;
}
int main(void){
    test3();
    return 0;
}


int test()
{
    return 0;
}
//-----------------------------

/// @brief 
/// @param total 左边的突出位置总数
/// @param apply 应用的星星
/// @return 
int print_matrix(int total,int apply){
    // 优先打印空格，最后换行
    for (int i=0;i<total-apply;i++){
        printf(" ");
    }
    for (int i=0;i<apply;i++){
        printf("*");
    }
    printf("*");
    for (int i=0;i<apply;i++){
        printf("*");
    }
    printf("\n");
    return 0;
}
int test2()
{
    //简单设计这个矩阵打印.....，记得我第一节课用了很奇妙的炫技
    for (int i=0;i<=3;i++){
        print_matrix(3,i);
    }
    for (int i=2;i>=0;i--){
        print_matrix(3,i);
    }
    return 0;
}
//-----------------------------
/// @brief 是的，是中文命名
/// @return 返回 true 或者 flase
int wan_num(int num)
{
    //计算是不是完数
    int yue_sum = 0;
    for (int i = 1; i < num; i++){
        if (num % i == 0 && num != i){
            yue_sum += i;
        }
    }
    if (yue_sum == num){
        return 1;
    }else{
        return 0;
    }
}

int test1()
{
    for (int i=1;i<=1000;i++){
        if (wan_num(i)){
            printf("%d ",i);
        }
    }
    return 0;
}