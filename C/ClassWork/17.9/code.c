#include <stdio.h>
#include <string.h>

int f1(int x)
{
    return ((x > 0) ? f1(x - 1) + f1(x - 2) : 1);
}

int test1()
{
    printf("%d", f1(3));
}

// 冒泡排序
void Sort(char Num[])
{
    // O(n^n)
    for (int k = 0; k < strlen(Num) - 1; k++)
    {
        // 一次排序
        for (int i = 0; i < strlen(Num) - k - 1; i++)
        {
            if (Num[i] < Num[i + 1])
            {
                char temp = Num[i + 1];
                Num[i + 1] = Num[i];
                Num[i] = temp;
            }
        }
    }
}

void fun(char str[])
{
    char Num[50];
    for (int i = 1, k = 0; i < strlen(str) - 1; i++, k++)
    {
        Num[k] = str[i];
    }
    Sort(Num);
    for (int i = 1, k = 0; i < strlen(str) - 1; i++, k++)
    {
        str[i] = Num[k];
    }
}

int test2()
{
    char str[100];
    gets(str);
    fun(str);
    puts(str);
}

#include <stdio.h>
#include <string.h>

void ages(int age[],int d[]){
    for (int i=0;i<10;i++){
        if (age[i]<100)d[age[i]/10]++;else d[10]++;
    }
}
/*
写死
*/
int test3(){
    int age[11]={0};
    for (int i=0;i<10;i++){
        scanf("%d",&age[i]);
    }
    int d[11]={0};
    ages(age,d);
    for (int i=0;i<=10;i++){
        printf("d[%d]=%d\n",i,d[i]);
    }
}


// 数据限定：小于 100
#include<stdio.h>
int deletes(int pos,int num[],int hash[]){
    for (int i=0;i<pos;i++){
        hash[num[i]]=5;
    }
}

int test15(){
    int pos;
    printf("输入数字：");
    scanf("%d",&pos);
    int num[pos];
    for (int i=0;i<pos;i++){
        scanf("%d",&num[i]);
    }
    // 去重
    int hash[20]={0};
    deletes(pos,num,hash);
    for (int i=0;i<20;i++){
        if (hash[i]==5) printf("%d ",i);
    }
}

#include <stdio.h>
#include <string.h>
void char_convert(char s[],char t[]){
    for (int i=0,k=0;i<strlen(s);i++){
        if (s[i]%2!=0){
            t[k]=s[i];
            k+=1;
        }
    }
}

int test16(){
    char wat[50];
    char t[50];
    gets(wat);
    char_convert(wat,t);
    for (int i=0;i<strlen(t);i++){
        printf("%c",t[i]);
    }
    // 因为题目不能带 \n 所以不用 puts
}


#include<math.h>
#include<stdio.h>
// 素数表筛选
int isPrime(int target) {
	int i = 0;
	if (target <= 1) {
		printf("illegal input!\n");//素数定义
		return -1;
	}
	for (i = 2; i <= sqrt(target); i++) {
		if (target % i == 0)
			return 0;
	}
	return 1;
}

int getNoPrime(int max,int numList[]){
    int k=0;
    for (int i=2;i<max;i++){
        if (!isPrime(i)){
          numList[k]=i;
          ++k;
        }
    }
    return k;
}

// @17
int main(){
    int max,much;
    int numList[100]={0};
    printf("请输入5到100数字:");
    scanf("%d",&max);
    much=getNoPrime(max,numList);
    printf("大于1小于%d的有%d个非素数\n",max,much);
    for (int i=0,k=0;i<much;i++,k++){
        if (k==5) printf("\n"),k=0;
        printf("%4d",numList[i]);
    }
}