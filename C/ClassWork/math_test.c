//@数学测试
#include <stdio.h>
#include <math.h>

/*
The math library must be linked in when building the executable. How to do this varies by environment, but in Linux/Unix, just add -lm to the command:

gcc test.c -o test -lm
*/

// start
int main(void){
    double test_1,result;
    scanf("%lf",&test_1);
    result=sqrt(test_1);
    printf("%lf\n",result);
}


