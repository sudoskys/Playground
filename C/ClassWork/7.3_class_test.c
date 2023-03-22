// 测试

#include <stdio.h>
#include <math.h>


int code() {
	// 嵌套
	double sample,x,y;
	scanf("%lf",&sample);
	if (sample<1000) {
		if (sample>=0) {
			x=sqrt(sample);
			y=-x;
			if (x!=0) {
				printf("%.2lf,%.2lf",x,y);
			} else {
				printf("0");
			}
		} else {
			printf("%.0lf在实数范围内没有平方根",sample);
		}
	} else {
		printf("输入数值大了");
	}
	return 0;
}

int code2() {
	// 绝对值
	int sample;
	scanf("%d",&sample);
	if (sample<0) {
		sample=-(sample);
	}
	printf("%d",sample);
	return 0;
}

int code3() {
	// 幼儿园
	int age;
	scanf("%d",&age);
	if (age>=2&&age<=6) {
		// 不冲突且有限情况，没必要选择语句，如果情况复杂就交给表处理。
		//具体用什么我也忘了，好像可以叫 状态机？
		if (age==2||age==3) {
			printf("small class");
		}
		if (age==4) {
			printf("middle class");
		}
		if (age==5||age==6) {
			printf("top class");
		}
	} else {
		printf("幼儿园暂不接收");
	}
	return 0;
}

int code4() {
	// 枚举所有月份.....
	int year,month,_day;
	scanf("%d-%d",&year,&month);
	switch(month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: {
			printf("31");
			break;
		}
		case 4:
		case 6:
		case 9:
		case 11: {
			printf("30");
			break;
		}
		case 2: {
			_day=((year%4==0&&year%100!=0)||(year%400==0))?29:28;
			printf("%d",_day);
			break;
		}
	}
	return 0;
}

int code5() {
	// 要是用字典就好实现，只好又枚举了，好像C也没有字典
	double above,last;
	double result;
	char smb;
	scanf("%lf%c%lf",&above,&smb,&last);
	switch(smb) {
		case '*': {
			result=(above*last);
			printf("%.2lf",result);
			break;
		}
		case '/': {
			if (((int)last%1==0)&&last<1) {
				printf("除数不能为0");
			} else {
				result=(above/last);
				printf("%.2lf",result);
			}
			break;
		}
		case '+': {
			result=(above+last);
			printf("%.2lf",result);
			break;
		}
		case '-': {
			result=(above-last);
			printf("%.2lf",result);
			break;
		}
		default:
			printf("运算符有误");
	}
	return 0;
}

int main() {
	code5();
}
