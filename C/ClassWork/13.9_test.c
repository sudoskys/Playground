#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main(){
    int str[5][20] = {0};
    for (int i = 0; i < 5; i++){
        for (int n = 0; n < 3; n++){
            scanf("%d", &str[i][n]);
        }
    }
    // 遍历计算
    int max[2]={-1,-1000},min[2]={-1,1000};
    for (int i = 0; i < 5; i++){
        int sum=0;
        for (int n = 0; n < 3; n++){
            sum+=str[i][n];
        }
        if (sum>max[1]){max[0]=i;max[1]=sum;}
        if (sum<min[1]){min[0]=i;min[1]=sum;}
    }
    printf("stu_maxorder=%d max=%d,",max[0]+1,max[1]);
    printf("stu_minxorder=%d min=%d",min[0]+1,min[1]);
}

int test4()
{
    int i = 0;
    char s[80] = {""}, max[80] = {""};
    scanf("%s", s);
    strcpy(max, s);
    for (i = 1; i < 5; i++)
    {
        scanf("%s", s);
        if (strcmp(max, s) < 0)
            strcpy(max, s);
    }
    printf("最长的字符串是：%s", max);
    return 0;
}
/*
int main(){
    int max,tar;
    char *matrix[5];
    for (int i=0;i<5;i++){
        gets(matrix[i]);
    }
    for (int i=0;i<5;i++){
        if (max<strlen(*matrix[i])){
            max=strlen(*matrix[i]);
            tar=i;
        }
    }
    printf("最长的字符串为：%s",matrix[tar]);
}
*/

int test3()
{
    // 链接字符串的实现
    printf("请输入两个字符串：");
    char str[50], str1[50], str2[100];
    int n, i = 0;
    scanf("%s", str);
    scanf("%s", str1);
    for (; i < strlen(str); i++)
    {
        str2[i] = str[i];
    }
    for (int n = 0; n < strlen(str1); n++, i++)
    {
        str2[i] = str1[n];
    }
    printf("合并后的字符串为");
    puts(str2);
}

int test2()
{
    printf("请输入一字符串：");
    char str[50], str1[50];
    int n, i = 0, isr = 1;
    scanf("%s", str);
    n = strlen(str) - 1;
    for (int i = 0; i < n / 2; i++)
    {
        if (str[i] != str[n - i])
        {
            isr = 0;
            break;
        }
    }
    if (isr)
    {
        printf("%s是回文。", str);
    }
    else
    {
        printf("%s不是回文。", str);
    }
}

int test1()
{
    double str[50];
    double sum = 0;
    for (int i = 0; i < 5; i++)
    {
        scanf("%lf", &str[i]);
        sum += str[i];
    }
    double ave = sum / 5.0;
    printf("ave=%.3lf大于平均值的数:", ave);
    for (int i = 0; i < 5; i++)
    {
        if (str[i] > ave)
        {
            printf("%.lf ", str[i]);
        }
    }
    return 0;
}