//@8，test
#include <stdio.h>
#include <string.h>
// 嵌套实现
double xx(int item) {
	int index=1;
	double result=1.0;
	while (index<=item) {
		result*=index;
		index++;
	}
	return result;
}
int x3(int a) {
	return (a*a*a);
}

int length(int num) {
	int len=1;
	while (num/=10) {
		++len;
	}
	return len;
}
/*
above is func
*/
int test7(){
	int k=0;
	while(k=1){
		printf("a");
		k++;
	}
}
int main(void) {
	test7();
}

int test6() {
	int appecpt;
	scanf("%d",&appecpt);
	if (appecpt>0) {
		char ch[length(appecpt)];
		snprintf(ch, sizeof(ch), "%d", appecpt);
		// int to numy list
		for (int i=0; i<sizeof(ch); i++) {
			printf("%c,",ch[sizeof(ch)-i-1]);
		}
		printf("是%d位数",sizeof(ch));
	}else{
	printf("输入有误"); 
	} 
	return 0;
}


int test5() {
	int max=-1;
	char ch;
	int count=0,item;
	do {
		scanf("%d",&item);
		if (max<item) {
			max=item;
		}
	} while((ch=getchar())!='\n');
	if (max<0) {
		printf("无有效输入");
	} else {
		printf("max=%d",max);
	}
	return 0;
}


int test4() {
	double sum=0;
	int index = 0,item,d_count;
	char ch;
	int appecpt;
	scanf("%d",&appecpt);
	if (appecpt==0) {
		printf("输入的整数个数为0");
	} else {
		do {
			scanf("%d", &item);
			if (item%2==0) {
				if (item!=0) {
					sum+=item;
					index++;
				}
			} else {
				d_count++;
			}
			if (item==0) {
				d_count--;
			}
		} while((ch=getchar())!='\n');
	}
	if (index>0) {
		printf("输入了%d个偶数，平均值为%.2lf",index,sum/index);
	} else {
		if (d_count>=0) {
			printf("输入的整数中没有偶数");
		} else {

		}
	}
	return 0;
}
int test3() {
	double sum=0;
	int count=0,item,d_count=0;
	char ch;
	do {
		scanf("%d",&item);
		if (item%2==0) {
			if (item!=0) {
				sum+=item;
				count++;
			}
		} else {
			d_count++;
		}
		if (item==0) {
			d_count--;
		}
	} while((ch=getchar())!='\n');
	if (count>0) {
		printf("输入了%d个偶数，平均值为%.2lf",count,sum/count);
	} else {
		if (d_count>=0) {
			printf("输入的整数中没有偶数");
		} else {
			printf("输入的整数个数为0");
		}
	}
	return 0;
}

int test2() {
	// 这个题目是要遍历所有位数？ O(^n)
	// 暴力遍历所有三位数，换取算法简便性
	int x,y,z;
	for (int i=100; i<1000; i++) {
		x=(int)(i/100);
		y=(int)(i-x*100)/10;
		z=(int)(i-x*100-y*10/1);
		if(i==x3(x)+x3(y)+x3(z)) {
			printf("%d ",i);
		}
	}
	return 0;
}


int test1() {
	double sum=0;
	for (int i=1; i<=10; i++) {
		// 循环
		sum+=xx(i);
	}
	printf("%.lf",sum);
	return 0;
}

