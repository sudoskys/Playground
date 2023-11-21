#include <iostream>
using namespace std;
const int MAXSIZE = 100; // 定义最大长度


typedef struct {
    int data[MAXSIZE];
    int length;
} SqList; // 定义顺序表类型

typedef int ElemType;
typedef struct LNode{
    ElemType data;
    struct LNode *next;
}LNode,*LinkList;

// 定义二叉树结点的数据类型
struct TreeNode {
    char val;
    TreeNode* left;
    TreeNode* right;
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

// 交换二叉树中所有结点的左右孩子
void swapTree(TreeNode* node) {
    if (!node) {
        return;
    }
    TreeNode* tmp = node->left;   // 交换左右孩子
    node->left = node->right;
    node->right = tmp;
    swapTree(node->left);         // 递归处理左子树
    swapTree(node->right);        // 递归处理右子树
}

// 中序遍历打印二叉树
void inorderTraversal(TreeNode* root) {
    if (!root) {
        return;
    }
    inorderTraversal(root->left);
    cout << root->val;
    inorderTraversal(root->right);
}

int main() {
    TreeNode* T = buildTree();     // 根据输入构建一棵二叉树
    TreeNode* NT = new TreeNode(T->val);   // 复制二叉树，构建新的二叉树
    NT->left = T->left;
    NT->right = T->right;
    swapTree(NT);   // 交换新二叉树中所有结点的左右孩子
    cout << "交换二叉树每个结点的左右孩子前，中序遍历序列：";
    inorderTraversal(T);
    cout << endl;
    cout << "交换二叉树每个结点的左右孩子后，中序遍历序列：";
    inorderTraversal(NT);
    cout << endl;
    return 0;
}
