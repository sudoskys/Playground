#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 100 // 定义最大容量

typedef int SElemType; // 定义栈元素类型

typedef struct {
    SElemType *base; // 栈底指针
    SElemType *top; // 栈顶指针
    int stacksize; // 当前已分配的存储空间，以元素为单位
} SqStack;

// 初始化栈
void InitStack(SqStack &S) {
    S.base = (SElemType *) malloc(MAXSIZE * sizeof(SElemType)); // 动态分配存储空间
    if (!S.base) // 分配失败
        exit(1);
    S.top = S.base; // 空栈的栈顶指针是指向栈底的
    S.stacksize = MAXSIZE; // 最大容量
}

// 销毁栈
void DestroyStack(SqStack &S) {
    free(S.base); // 释放存储空间
    S.base = S.top = NULL;
    S.stacksize = 0;
}

// 清空栈
void ClearStack(SqStack &S) {
    S.top = S.base; // 栈顶指针指向栈底
}

// 判断栈是否为空
int StackEmpty(SqStack S) {
    if (S.top == S.base) // 栈顶等于栈底，栈为空
        return 1;
    else
        return 0;
}

// 求栈的长度
int StackLength(SqStack S) {
    return S.top - S.base; // 栈顶减去栈底即为长度
}

// 获取栈顶元素
int GetTop(SqStack S, SElemType &e) {
    if (S.top == S.base) // 栈为空
        return 0;
    e = *(S.top - 1); // 取出栈顶元素
    return 1; // 取出成功
}

// 入栈
void Push(SqStack &S, SElemType e) {
    if (S.top - S.base == S.stacksize) // 栈满
        return;
    *S.top++ = e; // 将元素压入栈中
}

// 出栈
int Pop(SqStack &S, SElemType &e) {
    if (S.top == S.base) // 栈为空
        return 0;
    e = *(S.top - 1); // 取出栈顶元素
    --S.top; // 栈顶指针减1
    return 1;
}

// 遍历栈中每个元素
void StackTraverse(SqStack S, void(*visit)(SElemType)) {
    SElemType *p = S.base; // 从栈底开始遍历
    while (p != S.top) { // 遍历直到栈顶
        visit(*p); // 访问元素
        p++; // 移动指针
    }
    printf("\n");
}

// 访问元素
void visit(SElemType e) {
    printf("%d ", e);
}

int main() {
    SqStack S;
    SElemType e;

    // 初始化栈
    InitStack(S);

    int n, item, i;
    // printf("请输入顺序栈数据元素个数: ");
    scanf("%d", &n);

    // printf("请输入顺序栈%d个整数:n", n);
    for (i = 1; i <= n; i++) {
        scanf("%d", &item);
        Push(S, item); // 元素进栈
    }

    printf("栈中的元素依次为：");
    StackTraverse(S, visit); // 遍历并输出元素

    if (Pop(S, e)) // 出栈
        printf("弹出的栈顶元素e=%d\n", e);

    printf("栈空否：%d(1:空 0:否)\n", StackEmpty(S)); // 判断是否为空栈

    GetTop(S, e); // 获取栈顶元素
    printf("栈顶元素 e=%d，栈的长度为%d\n", e, StackLength(S));

    ClearStack(S); // 清空栈

    printf("清空栈后，栈空否：%d(1:空 0:否)\n", StackEmpty(S)); // 判断是否为空栈

    DestroyStack(S); // 销毁栈
    return 0;
}

