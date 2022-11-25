# 题解

## 排序算法

编写函数 void fun，假设字符串长度为 n，除首、尾字符外，将其余 n-2 个字符按 ASCII 码降序排列

```c
// 当然是冒泡排序
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
```

## 年龄计数

编写函数 `void fun(int age[],int d[])`, 功能是：统计各年龄段的人数。
10 个年龄通过键盘输入并存放在 main 函数的 age 数组中；

要求在 main 函数中定义一个数组 d，使用 fun 函数把 0 至 9 岁年龄段的人数放在 d[0] 中，把 10 至 19 岁年龄段的人数放在 d[1] 中，把 20 至 29 岁年龄段的人数放在 d[2] 中，其余依此类推，最后把 100 岁（含 100）以上年龄的人数都放在 d[10] 中。

```c
#include <stdio.h>
#include <string.h>
void ages(int age[],int d[]){
    for (int i=0;i<10;i++){
        if (age[i]<100)d[age[i]/10]++;else d[10]++;
    }
}
int main(){
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
```

## 删去一维数组中相同数

编写函数 int fun，n 是数组中元素的个数，函数的功能是：删去一维数组中所有相同的数，只保留一个。数组中的数已按由小到大的顺序排列，函数返回删除后数组中数据的个数。

```
输入：2 2 2 3 4 4 5 6 6 6 
输出：2 3 4 5 6
```

```c
// 数据限定：小于 100
#include<stdio.h>
void delete(int pos,int num[],int hash[]){
    for (int i=0;i<pos;i++){
        hash[num[i]]=5;
    }
}

int main(){
    int pos;
    printf("输入数字：");
    scanf("%d",&pos);
    int num[pos];
    for (int i=0;i<pos;i++){
        scanf("%d",&num[i]);
    }
    // 去重
    int hash[20]={0};
    delete(pos,num,hash);
    for (int i=0;i<20;i++){
        if (hash[i]==5) printf("%d ",i);
    }
}
```

## 删去字符串中 ASCll 值为偶数的字符

编写函数 void tun(chars U,chart u）, 实现将 s 所指字符串中 ASCll 值为偶数的字符删除，串中剩余学符形成一个新串放在 t 所指的数组中。

>// 因为题目不能带 \n 所以不用 puts

```c
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

int main(){
    char wat[50];
    char t[50];
    gets(wat);
    char_convert(wat,t);
    for (int i=0;i<strlen(t);i++){
        printf("%c",t[i]);
    }
}
```

## 非素数寻找

编写函数`int fun(int m,int xx[])`，实现将所有大于 1 小于 m 的非素数存入 xx 所指数组中，非素数的个数通过 k 返回。

此题在 Vscode 环境下，因为 math 库的问题会生成失败，需要使用

`gcc xxx -o xxx -lm`

来生成。

```c
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
    printf("请输入 5 到 100 数字：");
    scanf("%d",&max);
    much=getNoPrime(max,numList);
    printf("大于 1 小于%d 的有%d 个非素数、n",max,much);
    for (int i=0,k=0;i<much;i++,k++){
        if (k==5) printf("\n"),k=0;
        printf("%4d",numList[i]);
    }
}
```