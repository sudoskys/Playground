//@课程作业3.6
#include <stdio.h>

void morning() {
	printf("I said,\"Good morning\"");
}

void hello() {
	printf("c:\\program\\hello.c");
}

void work() {
	printf("完成率为100%%");
}
int change(int a,int b) {
	//
	int temp;
	temp = a;
	a=b;
	b=temp;
	printf("%d,%d",a,b);
	return 0;
}
int main(int argc,char *argv[]) {
	int x=10, y=20;
	change(x,y);
}