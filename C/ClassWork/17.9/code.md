# 题解

## 排序算法

编写函数void fun，假设字符串长度为n，除首、尾字符外，将其余n-2个字符按ASCII码降序排列

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

编写函数 `void fun(int age[],int d[])`,功能是：统计各年龄段的人数。
10个年龄通过键盘输入并存放在main函数的age数组中；

要求在main函数中定义一个数组d，使用fun函数把0至9岁年龄段的人数放在d[0]中，把10至19岁年龄段的人数放在d[1]中，把20至29岁年龄段的人数放在d[2]中，其余依此类推，最后把100岁（含100）以上年龄的人数都放在d[10]中。

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

编写函数int fun，n是数组中元素的个数，函数的功能是：删去一维数组中所有相同的数，只保留一个。数组中的数已按由小到大的顺序排列，函数返回删除后数组中数据的个数。

```
输入：2 2 2 3 4 4 5 6 6 6 
输出：2 3 4 5 6
```


```c
// 数据限定：小于 100
#include<stdio.h>
int delete(int pos,int num[],int hash[]){
    for (int i=0;i<pos;i++){
        hash[num[i]]=5;
    }
}

int main(){
    int pos;
    printf("输入个数：");
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

## 删去字符串中ASCll值为偶数的字符

编写函数void tun(chars U,chart u）,实现将s所指字符串中ASCll值为偶数的字符删除，串中剩余学符形成一个新串放在t所指的数组中。

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
    puts(t);
}
```