#include <stdio.h>
#include <stdlib.h>

// 定义单向链表的节点
typedef struct node
{
    int num;
    float score;
    struct node *next;
} Node;

// 创建一个单向链表
Node *createLinkedList()
{
    Node *head = NULL;
    Node *p, *q;
    int num;
    float score;
    int n = 4; // 定义学生数量
    // 输入学生信息
    for (int i = 0; i < n; i++)
    {
        scanf("%d,%f", &num, &score);
        // 创建新的学生节点
        p = (Node *)malloc(sizeof(Node));
        p->num = num;
        p->score = score;
        p->next = NULL;

        if (head == NULL)
        {
            // 创建链表的头节点
            head = p;
        }
        else
        {
            // 将新节点插入到链表中
            q->next = p;
        }
        q = p;
    }

    return head;
}

// 删除链表中成绩不及格的学生信息
void deleteFailedStudents(Node *head)
{
    Node *p, *q;

    // 判断头结点是否成绩不及格
    if (head->score < 60)
    {
        p = head;
        head = head->next;
        free(p);
    }

    // 扫描链表中的所有节点
    p = head;
    q = head->next;
    while (q != NULL)
    {
        if (q->score < 60)
        {
            p->next = q->next;
            free(q);
            q = p->next;
        }
        else
        {
            p = q;
            q = q->next;
        }
    }
}

// 打印链表中的学生信息
void printLinkedList(Node *head)
{
    Node *p;
    p = head;
    while (p != NULL)
    {
        printf("%d,%.1f\n", p->num, p->score);
        p = p->next;
    }
}
int main()
{
    Node *head;

    // 创建链表
    head = createLinkedList();

    // 删除链表中成绩不及格的学生信息
    deleteFailedStudents(head);

    // 打印链表中的学生信息
    printLinkedList(head);

    return 0;
}
