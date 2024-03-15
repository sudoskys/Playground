/*
* @File name:test
* @Description: test
*/

#include <stdio.h>
#include <string.h>
#define N 100

// @问题一，采用遍历装载
/*
@desc 计数
@s 原始数组
@p 计数数组
*/
void fun(char s[], int p[]){
    int i, j;
    for (i = 0; s[i] != 0; i++){ /*外循环扫描/遍历字符数组*/
        // 遍历字符，判定是否要增加索引键的值
        for (j = 0; j < 26; j++){
            if (s[i] == 'a' + j){
                // 26个字母的顺序数对应索引
                p[j]++;
                break;
            }
        }
    }
}
int test1(){
    // 初始化
    int i, c[26] = {0};
    char str[N];
    printf("请输入一个字符串：");
    gets(str);
    fun(str, c);
    // 遍历二十六个字母索引，如果存在就输出
    for (i = 0; i < 26; i++)
        if (c[i])
            printf("%c:%d ", 'a' + i, c[i]); /*输出字母a-z及其出现的次数*/
    return 0;
}

//@问题二，采用浮标
void long_word(char s[],char t[]){
    int start=-1,end=-1,i=0,sv=0,ev=0;
    for (i=0;s[i]!='\0';i++){
        if ((s[i]==' ')||(s[i+1]=='\0')){
            // 遍历到 空格 或 末尾，移动浮标
            sv=ev;ev=i;
            // 如果右浮标在尾部则进一，来输出最后一位
            if (s[i+1]=='\0') ev+=1;
            // 计算差值，更新峰值
            if ((ev-sv)>(end-start)){
                start=sv;end=ev;
            }
        }
    }
    // 如果左浮标不在首位则进一，来忽略空格
    if (start!=0)start+=1;
    // TEST
    printf("%d-%d\n",start,end);
    // 填写
    int k;
    for (k=0;start<end;start++,k++)t[k]=s[start];
    t[k]='\0';
}

int main(){
    char s[100],t[100];
    gets(s);
    long_word(s,t);
    for (int i=0;t[i]!='\0';i++){
        printf("%c",t[i]);
    }
}

// @问题三 函数
void deletex(char str[])
{
	int i=0,j=0;
	for(;(str[i]!='\0')&&(str[i]=='*');i++,j++){}
	for(;str[i]!='\0';i++){
		if(str[i]!='*'){
			str[j++]=str[i];
        }
	}
	str[j]='\0';
    
}

int test3(){
	char a[100];
	gets(a);
    deletex(a);
    printf("%s",a);
	return 0;
}

// @问题四 
int pos(char str[],char ch){
    int i;
    for(i=strlen(str)-1;i>=0;i--){
        if(str[i]==ch){
            return i+1;
        }
    }
    return -1;
}

int test4(){
    char a[100],b;
    int k;
    printf("字符串：");
    gets(a);
    printf("寻找字符：");
    scanf("%c",&b);
    k=pos(a,b);
    printf("字符位置：%d",k);
	return 0;
}

void max(int a[],int m){
    int i,j,t,count=0;
    for(i=0;i<m-1;i++){
        for(j=0;j<m-1-i;j++){
            if(a[j]>a[j+1]){
                t=a[j];
                a[j]=a[j+1];
                a[j+1]=t;
            }
        }
    }
}

int test5(){
	int s[100],m,i;
    scanf("%d",&m);
    printf("输入%d个整数\n",m);
    for(i=0;i<m;i++){
        scanf("%d",&s[i]);
    }
    max(s,m);
    for(i=0;i<m;i++)
        printf("%d ",s[i]);
	return 0;
}


#define  M  3
#define  N  4
void min(int tt[M][N],int pp[N] ){
    int i,j,min;
    for(i=0;i<N;i++){
        // 列
        min = tt[0][i];
        for(j = 0; j < M; j++)
            // 行
            if (min > tt[j][i])
                min = tt[j][i]; //具体元素 j i
        pp[i] = min;
    }
}
int test6(){
    int t[M][N];
    int p[N],i,j,k;
    for(i=0;i<M;i++){
        for(j=0;j<N;j++)
            scanf("%d",&t[i][j] );
    }
    min(t,p);
    printf("The result  is:\n" );
    for(k=0;k<N;k++){
        printf("%d ",p[k]);
    }
}