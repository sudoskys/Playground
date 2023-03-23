#include <stdio.h>
#include <stdlib.h>

// 单链表节点结构体
typedef struct node {
    int data;
    struct node *next;
} Node;

// 单链表结构体
typedef struct list {
    Node *head;
} List;

// 初始化单链表
List* init_list() {
    List *list = (List*)malloc(sizeof(List));
    list->head = NULL;
    return list;
}

// 向单链表指定位置插入节点
void insert_node(List *list, int index, int data) {
    Node *node = (Node*)malloc(sizeof(Node));
    node->data = data;

    if (index == 0) {
        node->next = list->head;
        list->head = node;
    }
    else {
        Node *current = list->head;
        int i = 0;

        while (i < index - 1 && current != NULL) {
            current = current->next;
            i++;
        }

        if (current != NULL) {
            node->next = current->next;
            current->next = node;
        }
    }
}

// 在单链表中寻找指定值并插入节点
void insert_node_by_value(List *list, int value, int data) {
    Node *node = (Node*)malloc(sizeof(Node));
    node->data = data;

    Node *current = list->head;

    while (current != NULL && current->data != value) {
        current = current->next;
    }

    if (current != NULL) {
        node->next = current->next;
        current->next = node;
    }
}

// 删除单链表指定位置的节点
void delete_node(List *list, int index) {
    if (list->head == NULL) {
        return;
    }

    if (index == 0) {
        Node *node = list->head;
        list->head = node->next;
        free(node);
    }
    else {
        Node *current = list->head;
        int i = 0;

        while (i < index - 1 && current != NULL) {
            current = current->next;
            i++;
        }

        if (current != NULL && current->next != NULL) {
            Node *node = current->next;
            current->next = node->next;
            free(node);
        }
    }
}

// 销毁单链表
void destroy_list(List *list) {
    Node *current = list->head;

    while (current != NULL) {
        Node *node = current;
        current = current->next;
        free(node);
    }

    free(list);
}

// 打印单链表
void print_list(List *list) {
    Node *current = list->head;

    while (current != NULL) {
        printf("%d ", current->data);
        current = current->next;
    }

    printf("n");
}

// 测试单链表的实现
int main() {
    List *list = init_list();

    insert_node(list, 0, 1);
    insert_node(list, 1, 2);
    insert_node(list, 2, 3);

    print_list(list);

    insert_node_by_value(list, 2, 4);

    print_list(list);

    delete_node(list, 2);

    print_list(list);

    destroy_list(list);

    return 0;
}


