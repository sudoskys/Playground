#include <stdio.h>


int main(){
{int a[6][6],i,j;
for(i=1;i<6;i++)
for(j=1;j<6;j++)
a[i][j]=(i/j)*(j/i);
for(i=1;i<6;i++)
{for(j=1;j<6;j++)
printf("%2d",a[i][j]);
printf("\n"); }
}
}

int test6(){
    int array[2][3];
    int arrays[3][2];
    int i,j;
    for(i=0;i<2;i++){
        for(j=0;j<3;j++){
            scanf("%d",&array[i][j]);
        }
    }
    for(i=0;i<2;i++){
        for(j=0;j<3;j++){
           arrays[j][i]=array[i][j];
        }
    }
    printf("array b:\n");
    for(i=0;i<3;i++){
        for(j=0;j<2;j++){
            printf("%5d",arrays[i][j]);
        }
        printf("\n");
    }
    return 0;
}



int test5(){
    int num[3][3] = {0};
    // 定义坐标
    char ch;int i = 0, j = 0;
    // 不聪明的写法...但是赶作业，go on！
    do
    {
        scanf("%d", &num[i][j]);
        j++;
    } while ((ch = getchar()) != '\n');
    j=0;i+=1;
    do
    {
        scanf("%d", &num[i][j]);
        j++;
    } while ((ch = getchar()) != '\n');
    j=0;i+=1;
    do
    {
        scanf("%d", &num[i][j]);
        j++;
    } while ((ch = getchar()) != '\n');
    int max=0;
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            if (num[i][j]>max){
                max=num[i][j];
            }
        }
    }
    printf("%d",max);
}




int test4(){
    int num[3][3] = {0};
    // 定义坐标
    char ch;int i = 0, j = 0;
    do
    {
        scanf("%d", &num[i][j]);
        if (j == 2){i++;j = 0;}else{j++;}
    } while ((ch = getchar()) != '\n');
    int row=0,column=0;
    printf("Row totals:");
    int sum=0;
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            sum+=num[i][j];
            if (j==2){
                if (i==2){
                    printf("%d",sum);
                }else{
                    printf("%d ",sum);
                } 
                 sum=0;
            }
        }
    }
    printf("\nColumn totals:");
    sum=0;
    for(i=0;i<3;i++){
        for(j=0;j<3;j++){
            sum+=num[j][i];
            if (j==2){
                if (i==2){
                    printf("%d",sum);
                }else{
                    printf("%d ",sum);
                } 
                 sum=0;
            }
        }
    }
}


int test1(){
    int i, j, x;
    for (j = 4;j>0; j--)
    {
        for (i = 1; i <= 4; i++)
        {
            x = (j - 1) * 4 + i;
            printf("%4d", x);
        }
        printf(" \n");
    }
}


int test2(void)
{
    //int a[4][4] = {1, 2, 3, 4, 2, 2, 5, 6, 3, 5, 3, 7, 8, 6, 7, 4};
    int a[4][4]={//对称矩阵
      {5,6,9,4},
      {6,8,7,9},
      {9,7,8,9},
      {4,9,9,4},
      };
    int i, j, found = 0;
    for (j = 0; j < 4; j++)
    {
        for (i = 0; i < 4; i++)
            if (a[i][j] != a[j][i])
            {
                found = 1;
                break;
            }
        if (found == 1)
            break;
    }
    if (found != 0)
        printf("该二维数组不对称\n");
    else
        printf("该二维数组对称\n");
    return 0;
}

int test3(){
int m[3][3] = {{1}, {2}, {3}};
int n[3][3] = {1, 2, 3};
printf("%d，", m[1][0] + n[0][0]);
    printf("%d\n", m[0][1] + n[1][0]);
}




