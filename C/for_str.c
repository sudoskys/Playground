#include <stdio.h>

int main(int argc, char *argv[])
{   // 传入参数个数和参数具体数组
    int i = 0;

    // go through each string in argv
    // 跳过第一个程序名
    printf("test:first arg is %s\n",argv[0]);
    printf("test:sencond arg is %s\n",argv[1]);
    for(i = 1; i < argc; i++) {
        printf("arg %d: %s\n", i, argv[i]);
    }
    
    // let's make our own array of strings
    char *states[] = {
        NULL,"California", "Oregon",
        "Washington", "Texas"
    };
    int num_states = 4;
    // 遍历数组！
    for(i = 0; i < num_states; i++) {
        printf("state %d: %s\n", i, states[i]);
    }

    return 0;
}
