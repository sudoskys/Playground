#include <stdio.h>

int test3(){
    int n;
    scanf("%d", &n);
    int array[n][n],fail=0;
    int i, j;
    for (i = 0; i < n; i++){
        for (j = 0; j < n; j++){
            scanf("%d", &array[i][j]);
        }
    }
    for (i = 1; i < n; i++){
        // 重置行状态，如果某行没有零，就break出来
        int haszero=0;
        for (j = 0; j < n; j++){
            if (array[i][j]==0 ){
                haszero=1; // 合格
            }
        }
        // 如果状态没有变动
        if (haszero==0){
            fail=1;
            break;
        }
    }
    if (fail) printf("NO");
    else printf("YES");
}

int main(){
    test3();
}

int test2(){
    int i, j, k, n = 7, a[14][14];
    for (i = 1; i <= n; i++)
        a[i][1] = a[i][i] = 1;
    // 确定最后一行的数据

    // 填充数据
    for (i = 3; i <= n; i++)
        for (j = 2; j <= i - 1; j++)
            a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
    // 遍历二维表
    for (i = 1; i <= n; i++)
    {
        for (j = 1; j <= i; j++) 
            printf("%4d", a[i][j]);
        printf("\n");
    }
}

int test1(){
    int array[3][3];
    int i, j;
    for (i = 0; i < 3; i++)
    {
        for (j = 0; j < 3; j++)
        {
            scanf("%d", &array[i][j]);
        }
    }
    int sum = 0, sums = 0;
    for (i = 0; i < 3; i++)
    {
        sum += (0 + array[i][2 - i]); // array[i][i]
    }
    printf("sum=%d", sum);
}