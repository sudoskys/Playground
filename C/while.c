//@while语句
#include <stdio.h>
/*
在C语言中，实际上没有真正的“布尔”类型
而是用一个整数来代替，0代表false，其它值代表true

上一个练习中表达式i < argc实际上值为1或者0，
并不像Python是显式的Ture或者False。

这是C语言更接近计算机工作方式的另一个例子，
因为计算机只把值当成数字。
*/

int main2(int argc, char *argv[])
{
    // go through each string in argv

    int i = 0;
    while(i < argc) {
        printf("arg %d: %s\n", i, argv[i]);
        i++;
    }

    // let's make our own array of strings
    char *states[] = {
        "California", "Oregon",
        "Washington", "Texas"
    };

    int num_states = 4;
    i = 0;  // watch for this
    while(i < num_states) {
        printf("state %d: %s\n", i, states[i]);
        i++;
    }

    return 0;
}
int main(int argc,char *argv[])
{
    int i = 0;
    while(i<argc){
        printf("arg: %d:%s\n",i,argv[i]);
        i++;
    }
}