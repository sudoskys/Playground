#include <stdio.h>
int x = 10, y = 20;
int main()
{
    char cr = '\72';
    char a = 'a';
    char b = 'A';
    char c = '0';
    printf("%d\n", a);
    printf("%d\n", b);
    printf("%d\n", c);
    printf("%c\n", cr);
    printf("x=%d,y=%d\0", x, y);
}