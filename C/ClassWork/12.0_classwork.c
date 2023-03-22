//@11.5 课堂作业啊
#include <stdio.h>
/*
for(i=0;i<4;i++){
    for(j=0;j<4;j++){
       scanf("%d",&num[i][j]);  //输入元素
    }
}
*/
// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
int test1()
{
    int num[4][4] = {0};
    // 定义坐标
    char ch;int i = 0, j = 0;
    do
    {
        scanf("%d", &num[i][j]);
        if (j == 3){i++;j = 0;}else{j++;}
    } while ((ch = getchar()) != '\n');
    /**/
    for (int i = 0; i < 4; i++){
        for (int j = 0; j < 4; j++){
            if (i % 2 == 0 || j % 2 == 0){
                printf("%d ", num[i][j]);
            }
        }
    }
    return 0;
}

int main()
{
    int i;
    int x[3][3] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    for (i = 0; i < 3; i++)
        printf("%d", x[i][2 - i]);
    return 0;
}