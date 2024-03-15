#include <stdio.h>
#include <string.h>
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