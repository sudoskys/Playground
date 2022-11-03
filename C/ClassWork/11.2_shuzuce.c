//@11.5 课堂作业啊
#include <stdio.h>

int test5(){
    int num[16] = {0};
    char ch;int i = 0;
    do{
        scanf("%d", &num[i]);
        i++;
    }while ((ch = getchar()) != '\n');
    int target=num[15];
    int low=0;
    int high=15;
    int yesor=0;
    while (low<=high){
        int mid=(low+high)/2;
        if (num[mid]==target){
            yesor=mid+1;
            break;
        }else{
            if (num[mid]>target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
    }
    if (yesor==0){
        printf("Cannot find %d.",target);
    }else{
        printf("Has found %d,its position is %d.",target,yesor);
    }
    
}

int main(void){
    test5();
}

// 冒泡排序
void Sort(int Num[]) {
    //O(n^n)
    for (int k=0; k<sizeof(Num)+2-1; k++) {
        // 一次排序
        for (int i=0; i<sizeof(Num)+2-k-1; i++) {
            if (Num[i]>Num[i+1]) {
                int temp=Num[i+1];
                Num[i+1]=Num[i];
                Num[i]=temp;
            }
        }
    }
}

int test4(){
    int num[10] = {};
    char ch;
    int i = 0;
    do
    {
        scanf("%d", &num[i]);
        i++;
    } while ((ch = getchar()) != '\n');
    Sort(num);
    for (int i=9; i>=0; i--) {
        printf("%d ",num[i]);
    }
}

//----
int test3()
{
    int input;
    int num[6] = {0};
    scanf("%d", &input);
    int i;
    for (i = 0; input != 0; ++i)
    {
        num[i] = input % 2;
        input = input / 2;
    }
    for (int i = 5; i >= 0; i--)
    {
        printf("%d", num[i]);
    }
}

int test2(void)
{
    double num[10] = {};
    double deal[10] = {};
    char ch;
    int i = 0;
    do
    {
        scanf("%lf", &num[i]);
        i++;
    } while ((ch = getchar()) != '\n');
    int start = -1;
    for (int i = 0; i < 9; i++)
    {
        if (num[i] > num[9])
        {
            // 一次性状态机
            if (start == -1)
            {
                deal[i] = num[9];
                start = i;
            }
            deal[i + 1] = num[i];
        }
        else
        {
            deal[i] = num[i];
        }
    }
    for (int i = 0; i < sizeof(deal) / sizeof(deal[0]); i++)
    {
        printf("%.lf ", deal[i]);
    }
}

int test1(void)
{
    double num[10] = {};
    char ch;
    int i = 0;
    do
    {
        scanf("%lf", &num[i]);
        i++;
    } while ((ch = getchar()) != '\n');
    double reverse[10] = {};
    for (int i = sizeof(num) / sizeof(num[0]) - 1; i >= 0; --i)
    {
        reverse[sizeof(num) / sizeof(num[0]) - i - 1] = num[i];
    }
    for (int i = 0; i < 10; i++)
    {
        printf("%.lf ", reverse[i]);
    }
}