//@do while
#include <stdio.h>
int test2()
{
	int num;
	double sum=0;
	int index = 0;
	char ch;
	do{
		scanf("%d", &num);
		if (num >= 0){
			sum += num;
			++index;
		}
	} while ((ch=getchar())!='\n' && num>=0);
	if (index == 0){
			printf("数值个数为0，无法计算平均值");
	}else{
		printf("%.2lf", (sum/index));
	}
	return 0;
}

int main(void)
{
	test2();
}


