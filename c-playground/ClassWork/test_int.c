//@int类型测试
#include <stdio.h>
#include <ctype.h>
int main()
{
    short i = -32768;
    i -=1;
    printf("%d\n", i);
    printf("short:%ld\n", sizeof(short));
    printf("int:%ld\n", sizeof(int));
    printf("long:%ld\n", sizeof(long));
    printf("long long:%ld\n", sizeof(long long));
}