//@格式化字符串测试

#include <stdio.h>

// https://blog.csdn.net/chenmozhe22/article/details/109738852
// https://blog.nowcoder.net/n/957696b4e7b94be9ae5924de5b05bdd7
int main(int argc, char *argv[])
{
    int a = 3366;
    printf("a=%-08d,a%08d,a=%3d\n", a, a, a);
    short x = 32769;
    printf("%d\n", x);
    int k = 017, g = 111;
    printf("%d\n", k);
    printf("%x\n", g);
    char tests[] = "as";
    printf("%5s\n", tests);
}
#include <stdio.h>

int main2()
{
    int a = 123, b = 123456;
    printf("%5d\n", a);   // 默认右对齐，且最少取5位整数，多余5位全取，不足5位使用空格左面补全
    printf("%05d\n", a);  // 使用0代替空格，在左边补齐位数
    printf("%-5d\n", a);  // 左对齐，不足位数，使用空格补全
    printf("%-05d\n", a); // 左对齐，不足位数，还是用空格补全
    printf("%5d\n", b);   // 超过5位全取
}

/*
％d			//整型输出

％ld		//长整型输出

％o			//以八进制数形式输出整数

％x		   //以十六进制数形式输出整数，或输出字符串的地址

％u			//以十进制数输出unsigned型数据(无符号数)


注意：		%d与%u的区别是，有无符号的数值范围不同，也就是极限的值不同，不然数值打印出来会有误



％c			//用来输出一个字符

％s			//用来输出一个字符串

％f			//用来输出实数，以小数形式输出，默认情况下保留小数点6位

%.5f		//用来输出实数，保留小数点5位

％e			//以指数形式输出实数

％g			//根据大小自动选f格式或e格式，且不输出无意义的零
*/

/*
a=3366    ,a00003366,a=3366
*/