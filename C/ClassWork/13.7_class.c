#include <stdio.h>
#include <string.h>
#include <ctype.h>
int main()
{
    char str[50];
    int n,i = 0;
    scanf("%s",str);
    n = strlen(str) - 1;
    for (i = 0; i <= n; i++){
         if (isupper(str[i])){
            str[i]=tolower(str[i]);
         }
    }
    printf("%s", str);
    return 0;
}

int test4()
{
    char str[50];
    int n,num,i = 0;
    scanf("%s",str);
    n = strlen(str) - 1;
    for (i = 0; i <= n; i++){
         if (islower(str[i])){
            num+=1;
         }
    }
    printf("小写字母个数：%d", num);
    return 0;
}

int test3()
{
    char str[50],str2[50];
    int n, i, j = 0;
    scanf("%s %s",str,str2);
    if (strcmp(str,str2)>0){
        printf("%s",str);
    }else{
        printf("%s", str2);
    }
    return 0;
}


int test2()
{
    char str[50], c;
    int n, i, j = 0;
    gets(str);
    n = strlen(str) - 1;
    for (i = 0; i <= n; i++){
        if (str[i] != '*'){
            str[j] = str[i];
            j += 1;
        }
        if (i==n){
            str[j]='\0';
        }
    }
    printf("%s", str);
    return 0;
}

int test1()
{
    char str[10], c;
    int n, i, j;
    gets(str);
    n = strlen(str) - 1;

    for (i = 0; i <= n / 2; i++)
    {
        c = str[i];
        str[i] = str[n - i];
        str[n - i] = c;
    }
    printf("%s", str);
    return 0;
}