//@格式化字符串测试

#include <stdio.h>
// 测试
int main1(int argc, char *argv[])
{
    int x,y;
    scanf("%3d%*2d%d",&x,&y);
    printf("%d\n%d",x,y);
}

int main2(){
    float a;
    scanf("%f",&a);
    printf("%1.2f,%5.2e",a,a);
}

int main3(){
    double long_,wide_,s_;
    scanf("%lf,%lf",&long_,&wide_);
    s_=long_*wide_;
    printf("长为%1.2lf宽为%1.2lf的长方形面积为%2.2lf",long_,wide_,s_);
}
// https://blog.csdn.net/sinat_27382047/article/details/102297705

// [C变量](https://blog.csdn.net/chenmozhe22/article/details/109738852)

// https://blog.nowcoder.net/n/957696b4e7b94be9ae5924de5b05bdd7