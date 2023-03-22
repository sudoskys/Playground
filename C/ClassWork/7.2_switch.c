//@作业
# include <stdio.h>

int main1() {
	int num;
	scanf("%d",&num);
	switch(num) {
		case 3:
		case 4:
		case 5:        {
			printf("春季");
			break;
		}
		case 6:
		case 7:
		case 8:        {
			printf("夏季");
			break;
		}


		case 9:
		case 10:
		case 11:        {
			printf("秋季");
			break;
		}

		case 12:
		case 1:
		case 2: {
			printf("冬季");
			break;
		}
	}
return 0;
}

int main() {
	double num;
	int level;
	scanf("%lf",&num);
	/*
	不良的多选择,因为多if语句不能保证我们只执行一次赋值语句，针对不唯一情况，就会出现错误。
	最好还是使用 switch 语句或者
	*/
	if (num<60) {
		level=5;
	}
	if (num>100) {
		level=0;
	}
	if (num>=90&&num<100) {
		level=1;
	}
	if (num>=60&&num<70) {
		level=4;
	}
	if (num>=70&&num<80) {
		level=3;
	}
	if (num>=80&&num<90) {
		level=2;
	}
	switch(level) {
		case 1:        {
			printf("您的成绩是%.1lf,等级是A",num);
			break;
		}
		case 2:        {
			printf("您的成绩是%.1lf,等级是B",num);
			break;
		}
		case 3:        {
			printf("您的成绩是%.1lf,等级是C",num);
			break;
		}
		case 4: {
			printf("您的成绩是%.1lf,等级是D",num);
			break;
		}
		case 5: {
			printf("您的成绩是%.1lf,等级是E",num);
			break;
		}
		default: {
			printf("您输入的成绩无效");
		}
	}
	return 0;
}