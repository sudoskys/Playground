#include <stdio.h>
#include <string.h>
#include <ctype.h>

int main()
{
    // 不明所以的题目,直接说，只要偶数下标的偶数ascii即可
    char str[50],str1[50];
    int n, i = 0;
    scanf("%s", str);
    int num = 0;
    for (int i = 2;i<strlen(str); i+=2){
        if (str[i]%2==0){
           str1[num]=str[i];
           num+=1;
        }
    }
    printf("%s", str1);
    return 0;
}

int test4()
{
    // 使用 scanf 会导致回车问题，因为其机制导致....
    // 读 关于c语言中 scanf 对多行字符的输入问题
    char str[25],c,r;
    printf("输入字符串：");
    gets(str);
    printf("输入字符：");
    c=getchar();
    int num=0;
    for (int i = 0; i < strlen(str); i++){
        if (str[i]==c){
            num+=1;
        }
    }
    printf("%d", num);
    return 0;
}

int test3()
{
    // 擂台
    char str[25], str2[25], str3[25], str4[25];
    int n, i = 0;
    scanf("%s %s %s", str, str2, str3);
    if (strcmp(str, str2) > 0)
    {
        strcpy(str4, str);
    }
    else
    {
        strcpy(str4, str2);
    }
    if (strcmp(str4, str3) < 0)
    {
        strcpy(str4, str3);
    }
    printf("%s", str4);
    return 0;
}

int test2()
{
    char str[50], str2[25];
    int n, i = 0;
    scanf("%s %s", str, str2);
    strcat(str, str2);
    printf("%s", str);
    return 0;
}

int test1()
{
    char str[50];
    int n, i = 0;
    scanf("%s", str);
    n = strlen(str) - 1;
    if (str[n] == '*')
    {
        for (i = n; i > 0; i--)
        {
            if (str[i - 1] != '*')
            {
                str[i] = '\0';
                break;
            }
        }
    }
    printf("%s", str);
    return 0;
}