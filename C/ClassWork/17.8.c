#include <stdio.h>
void num()
{
    extern int x, y;
    int a = 15, b = 10;
    x = a - b;
    y = a + b;
}
int x, y;
int main2()
{
    int a = 7, b = 5;
    x = a - b;
    y = a + b;
    num();
    printf("%d,%d\n", x, y);
}

#include <stdio.h>
long Func(int n);
int main3()
{
    int i, n;
    scanf("%d", &n);
    if (n == 0)
        printf("0！=1");
    for (i = 1; i <= n; i++)
    {
        if (i == n)
        {
            printf("%d！=%ld", i, Func(i));
        }
        else
        {
            Func(i);
        }
    }
    return 0;
}
long Func(int n)
{
    static long p = 1; /*定义静态变量*/
    p = p * n;
    return p;
}

#include <stdio.h>
int s1, s2, s3;
int vs(int a, int b, int c)
{
    int v;
    v = a * b * c;
    s1 = a * b;
    s2 = b * c;
    s3 = a * c;
    return v;
}
int main4()
{
    int v, l, w, h;
    printf("输入长方体的长l，宽w，高h");
    scanf("%d %d %d", &l, &w, &h);
    v = vs(l, w, h);
    printf("v=%d,s1=%d,s2=%d,s3=%d", v, s1, s2, s3);
}


#include<stdio.h>
float pave, nave;//定义全局变量
int saver(int a[],int n)//a是一维整型数组，n是其长度
{
	int i, sum_pave = 0, sum_nave = 0;
	int k = 0,m = 0;
	for (i = 0; i < n; i++)
		if (a[i] < 0)
		{
			sum_pave += a[i];
			k++;
		}
		else if(a[i] > 0)
		{
			sum_nave += a[i];
			m++;
		}
	pave = (float)sum_pave / k;
	nave = (float)sum_nave / m;
}
int main()
{
    int num,num_list[20];
	printf("输入n：");
    scanf("%d",&num);
    printf("输入8个数据：");
    for (int i=0;i<num;i++){
        scanf("%d",&num_list[i]);
    }
	saver(num_list, num);
	/*调用全局变量输出*/
	printf("正数的平均值=%.2f,", nave);
	printf("负数的平均值=%.2f", pave);
	return 0;
}

