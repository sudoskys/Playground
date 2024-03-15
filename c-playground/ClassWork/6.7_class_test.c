//@作业
# include <stdio.h>

int test1(void){
    int ins;int x,y,z;
	int sum=0;
    scanf("%d",&ins);
    x=(ins/100);
    y=(ins-x*100)/10;
    z=(ins-x*100-y*10)/1;
	sum=sum+x*x*x;
    sum=sum+y*y*y;
    sum=sum+z*z*z;
    printf("%d%s",ins,(sum==ins)?("是水仙花数"):("不是水仙花数"));
}

int test2(){
   char *week[7] = {"一","二","三","四","五","六","日"},*well;
   int day;
   scanf("%d",&day);
   well=(day%7!=0)?(week[day%7-1]):(week[7-1]);
   printf("星期%s",well); 
}


int test3(){
   int x;
   double y;
   scanf("%d",&x);
   if (x<1){
   	  y=float(x);
   }
   if(10>x>=1){
      y=float(x*2-1);
   }
   if(x>=10){
   	  y=float(3*x-11);
   }
   printf("%.2lf",y);
   return 0;
}

int main(){
    test2();
}