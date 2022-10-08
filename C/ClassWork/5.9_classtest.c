// 测试程序 
#include <stdio.h>
#include <math.h> 

void test1(void){
	//次方 
	double x,y,result;
	scanf("%lf,%lf",&x,&y);
	result= pow(x,y);
	printf("%.2lf",result);
}

void test2(){
	// 暴力解法 
	int a,b,c;
	int a1,b1,c1;
	scanf("%d%d%d",&a,&b,&c);
	// 全部复制 
	a1=a;
	b1=b;
	c1=c;
	 
	a=b1;
	b=c1;
	c=a1;
	printf("a=%d,b=%d,c=%d",a,b,c);
}

void test3(){
	// 逆序输出
	int index,item;
	char scr[3];
	scanf("%s",scr);
	int sum=0;
	int haszero=1;
	for (int i=0;i<sizeof(scr);i++){
		//printf("run:%d\n",i);
		index=sizeof(scr)-i-1;
		item=scr[index];
		// 开始判定
		if (item!='0' && haszero==1){
			haszero=0;
		}
		// 开始输出
		if (haszero!=0){
			printf("");
			//printf("/%c/",item);
			//printf("%c",item);
		}else{
            printf("%c",item);
		}
	}
}

void test4(){
	// 采用简单方法 
	signed int init;
	scanf("%d",&init);
	// int if 
	if (init>5){
	 // convert
     	init = init-10;
	}
	printf("%d",init+4);
}


void test5(){
	double money; 
	int yuan,jiao,fen;
	// 位数?
	scanf("%lf",&money);
	money*=100;
	yuan=money/100;
	jiao=(money-yuan*100)/10;
	fen=(money-yuan*100-jiao*10)/1;
	printf("%d元%d角%d分",yuan,jiao,fen);
}
int main(){
	test5();
}