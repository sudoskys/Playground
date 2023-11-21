#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#define MaxSize 100

typedef char elemtype;
typedef struct
{
    int lvl, ord;  // 层次、本层序号（按满二叉树计算）
    elemtype data;
} node;

// 按顺序存储结构创建一颗二叉树
void create_binary_tree(node tree[], char *s, int len)
{
    int i = 1, j = 1, k = 1;
    while (i <= len) {
        tree[j].data = s[i];
        tree[j].lvl = k;
        tree[j].ord = j - pow(2, k - 1) + 1;
        if (j == pow(2, k) - 1 && j != len) k++;
        i++, j++;
    }
}

int search(node tree[], int n, elemtype x)
{
    int i;
    for (i = 1; i <= n; i++) {
        if (tree[i].data == x) return i;
    }
    return -1;
}

int parent(node tree[], int n, elemtype x)
{
    int i = search(tree, n, x);
    if (i == -1 || i == 1) return -1;
    return tree[i / 2].data;
}

elemtype left_child(node tree[], int n, elemtype x)
{
    int i = search(tree, n, x);
    if (i == -1 || 2 * i > n) return '#';
    return tree[2 * i].data;
}

elemtype right_child(node tree[], int n, elemtype x)
{
    int i = search(tree, n, x);
    if (i == -1 || 2 * i + 1 > n) return '#';
    return tree[2 * i + 1].data;
}

int main()
{
    char s[MaxSize], x;
    node tree[MaxSize];
    scanf("%s\n%c", s + 1, &x);
    int len = strlen(s + 1);
    create_binary_tree(tree, s, len);
    int idx = search(tree, len, x);
    if (idx == -1) {
        printf("无此结点\n");
    } else {
        elemtype p = parent(tree, len, x);
        if (p == -1) printf("无双亲结点\n");
        else printf("%c\n", p);
        elemtype l = left_child(tree, len, x);
        if (l == '#') printf("无左孩子结点\n");
        else printf("%c\n", l);
        elemtype r = right_child(tree, len, x);
        if (r == '#') printf("无右孩子结点\n");
        else printf("%c\n", r);
    }
    return 0;
}
