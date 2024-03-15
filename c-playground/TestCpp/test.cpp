#include <iostream>
#include <string>
#include <stack>
#include <vector>

using namespace std;

typedef int ElemType;

typedef struct LNode{
    ElemType data;
    struct LNode *next;
}LNode,*LinkList;


struct TreeNode {
    char val;
    struct TreeNode* left;
    struct TreeNode* right;
    TreeNode(char c) : val(c), left(NULL), right(NULL) {}
};


// 根据先序遍历构建一棵二叉树，其中 # 表示空结点
TreeNode* buildTree() {
    char c;
    cin >> c;
    if (c == '#') {
        return NULL;
    }
    TreeNode* root = new TreeNode(c);
    root->left = buildTree();
    root->right = buildTree();
    return root;
}
