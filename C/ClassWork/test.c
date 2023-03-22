#include <stdio.h>

int main2()
{
    int *p, a = 100;
    *p = a;
    printf("%d", *p);
}

int main3()
{
    int *p, a = 100;
    p = &a;
    printf("%d", *p);
}

int main4()
{
    int *p, a = 100;
    p = a;
    printf("%d", p);
}

int main5()
{
    int *p, a = 100;
    char *is;
    p = a;
    is = "我的";
    printf("%s", is);
}

// 使用 指针=&参数   定义要加 *
// 指针只是地址

// *&a = a 取地址后取内容
// &*pa 取内容后取地址
// 地址交换会导致参数交换


#include<stdio.h>

int main7(){
	int a[2][3]={{1,2,9},{4,5,6}};
	
	printf("%p\n",a);   //输出指针a数据，也就是指针a[0]的地址 
	printf("%p\n",a+1);   //输出a+1的数据 ，也就是a[1]的地址
	printf("%p\n",&a[0]);
	printf("%p\n",&a[1]);     //验证上述
	printf("%p\n",(*a)+1);  //输出的是a[0][1]的地址
	printf("%p\n",&a[0][1]);   //验证
	//
    printf("%d\n",*(*a+1)); 
    printf("%d\n",*(*a+2));  // 一维数组其实是语法糖
    printf("%d\n",*(*a+3)); // 获取单排个数

    // 
    printf("%d\n",*(*(a+1))); // 获取列
    printf("%d\n",*(*(a+1)));  //输出的是a[0]a[0]的值 
 	printf("%d\n",*(*(a+1)+2));  //输出的是a[1][1]的值 
}


// 函数取指针

int add(int a){
    return a+1;
}

int main(){
    int (*func)(int a);
    int r,test=1;
    func=add;
    r=func(test);
    printf("%d",r);
}
