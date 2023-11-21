#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode {
    char data;
    struct TreeNode * left_child;
    struct TreeNode * right_child;
} TreeNode;

// 按先序遍历顺序创建二叉树，用 # 补齐空结点
void create_binary_tree(TreeNode ** tree) {
    char ch;
    scanf("%c", &ch);
    if (ch == '#') {  // 空结点
        *tree = NULL;
    } else {
        *tree = (TreeNode *)malloc(sizeof(TreeNode));
        (*tree)->data = ch;
        create_binary_tree(&((*tree)->left_child));  // 创建左子树
        create_binary_tree(&((*tree)->right_child));  // 创建右子树
    }
}

// 中序遍历二叉树
void inorder_traversal(TreeNode * tree) {
    if (tree != NULL) {
        inorder_traversal(tree->left_child);
        printf("%c", tree->data);
        inorder_traversal(tree->right_child);
    }
}

// 复制二叉树
void copy_binary_tree(TreeNode ** new_tree, TreeNode * old_tree) {
    if (old_tree == NULL) {
        *new_tree = NULL;
    } else {
        *new_tree = (TreeNode *)malloc(sizeof(TreeNode));
        (*new_tree)->data = old_tree->data;
        copy_binary_tree(&((*new_tree)->left_child), old_tree->left_child);  // 复制左子树
        copy_binary_tree(&((*new_tree)->right_child), old_tree->right_child);  // 复制右子树
    }
}

// 交换二叉树每个结点的左右孩子
void swap_binary_tree(TreeNode * tree) {
    if (tree != NULL) {
        TreeNode * temp = tree->left_child;
        tree->left_child = tree->right_child;
        tree->right_child = temp;
        swap_binary_tree(tree->left_child);  // 递归交换左子树
        swap_binary_tree(tree->right_child);  // 递归交换右子树
    }
}

int main() {
    TreeNode * T, * NT;
    // printf("按先序方式依次输入二叉树中各结点的值，注意：用#补齐空结点。\n");
    create_binary_tree(&T);
    copy_binary_tree(&NT, T);  // 复制二叉树
    printf("交换二叉树每个结点的左右孩子前，中序遍历序列：");
    inorder_traversal(T);
    printf("\n");
    swap_binary_tree(NT);  // 交换二叉树每个结点的左右孩子
    printf("交换二叉树每个结点的左右孩子后，中序遍历序列：");
    inorder_traversal(NT);
    printf("\n");
    return 0;
}
