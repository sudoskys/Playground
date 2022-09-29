#include <stdio.h>
#include <ctype.h>

// 向前声明函数
// forward declarations
int can_print_it(char ch);
void print_letters(char arg[]);


// 打印函数2
void print_arg(int argc, char *argv[])
{    // 实际上是一个参数数组
    int i = 0;
    for (i=0;i<argc;i++){
        printf("this is %s\n",argv[i]);
    }
}

//打印函数
void print_arguments(int argc, char *argv[])
{
    int i = 0;
    for(i = 0; i < argc; i++) {
        print_letters(argv[i]);
    }
}

//打印字符
void print_letters(char arg[])
{
    int i = 0;

    for(i = 0; arg[i] != '\0'; i++) {
        char ch = arg[i];

        if(can_print_it(ch)) {
            printf("'%c' == %d ", ch, ch);
        }
    }

    printf("\n");
}

int can_print_it(char ch)
{
    return isalpha(ch) || isblank(ch);
}


int main(int argc, char *argv[])
{
    print_arg(argc, argv);
    return 0;
}
