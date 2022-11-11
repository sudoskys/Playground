//@test
#include <stdio.h>

int get_length(int x)
{
	int leng=0;
	while(x)
	{
		x/=10;
		leng++;
	}
	return leng;
}

int DigitSum(int n){
    //单位思考:取首字符并减位数
    n/(10*get_length(n));
}

main(){
    int result;
    int input;
    scanf("%d",&input);
    result=DigitSum(input);
    printf("%d",input,result);
}

/*
long age(int n,int ages,int init){
    if (n==1){
        return init;
    }else{
        return age(n-1,ages,init)+ages;
    }
}

main(){
    long result;
    //int input;
    //scanf("%d",&input);
    int input=6;
    result=age(input,3,7);
    printf("NO.%d age is %ld",input,result);
}
*/
/*
long xxx(int n){ 
    if(n==0||n==1){return 1;}
    else {return xxx(n-1)*n;}
}

main(){
    int input;
    long result;
    scanf("%d",&input);
    if (input>0){result=xxx(input);
    printf("%d!=%ld",input,result);
    }
    else {printf("%d<0,数据错误!",input);}
}
*/


/*
具体工程和完成题目还是差了很多，工程肯定不会钉死数据，而且会大量使用设计模式和模块化编程
*/

/*
long f2(int n,int k){
    long result=1;
    for (int i=0;i<k;i++){
        result*=n;
    }
    return result;
}

long f1(int n,int k){
    long _result=0;
    for (int i=1;i<=n;i++){
        _result+=f2(i,k);
    }
    return _result;
}

int main(){
// 这有什么好嵌套的？
    int n,k;
    long res=0;
    scanf("K=%d,N=%d",&k,&n);
    res=f1(n,k);
    printf("Sum of %d powers of  integers　from 1 to %d =%ld",k,n,res);
}
*/

/*
int max=-1000;
int putin(int put){
    max=(put>max)?put:max;
    return max;
}
int scan(){
    int in;
    scanf("%d",&in);
    putin(in);
}
int main(){
// 这有什么好嵌套的？
    for (int i=0;i<4;i++){
        scan();
    }
    printf("max=%d",max);
}
*/