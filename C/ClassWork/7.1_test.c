//@if
#include <stdio.h>

int rev_int(int tar){
	int r = 0;
	while(tar!=0) {
		r = r * 10 + tar % 10;
		tar = tar / 10;
	}
	return r;
}

int main(void) {
	int _n,n,m=1,r;
	char num[10]; 
	scanf("%d", &n);
	// 逆序并格式化字符串
	_n = rev_int(n);
	sprintf(num,"%d",_n);
	r=n;
	// 备份一份数据 
	while (n/10) {
		m+=1;
		n=n/10;
	}
	// 循环加除取位数
	if (m <= 5 && r > 0) {
		printf("输入的数值是%d位数，逆序为",m);
		// 数字装载数组 
		for (int i=0;i<m;i++){
			if (i==m-1) printf("%c",num[i]);  else printf("%c,",num[i]); 
		} 
	} else {
		printf("输入有误");
	}
	return 0;
}
