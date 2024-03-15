#include <iostream>
using namespace std;
const int MAXSIZE = 100; // 定义最大长度


typedef struct {
    int data[MAXSIZE];
    int length;
} SqList; // 定义顺序表类型
int locateElem(SqList L, int e) { // 查找元素e在顺序表L中的位置，若不存在返回0
    for (int i = 1; i <= L.length; i++) {
        if (equals(L.data[i], e)) {
            return i;
        }
    }
    return 0;
}


typedef int ElemType;
typedef struct LNode{
    ElemType data;
    struct LNode *next;
}LNode,*LinkList;

LinkList CreateList(int n)
{
    LinkList L,p,q;
    L=(LinkList)malloc(sizeof(LNode));
    L->next=NULL;
    q=L;
    for(int i=0;i<n;++i)
    {
        p=(LinkList)malloc(sizeof(LNode));
        cin>>p->data;
        p->next=NULL;
        q->next=p;
        q=p;
    }
    return L;
}




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




#include <iostream>
#include <cstring>
using namespace std;


// KMP 获取Next模式的数组，这是一个 O(n) 的算法
// @param t: the string to be found
// @param next: the next array of t
// @return the starting index of t in s, or 0 if t is not found in s
void GetNext(string t, int next[])
{
    // 请注意，我们这里是逐步匹配初始和探测的距离的，这个距离代表匹配成功的长度。

    // 然后 j 是匹配到的最长前缀的长度
    // i 是当前匹配到的位置
    int i = 1, j = 0;
    // next[0] is not used
    next[1] = 0;
    while (i < t.length()) {
        // 如果 j == 0 或者当前字符匹配成功（即 t[i] == t[j]），都令 i++，j++，继续匹配下一个字符
        // j==0，没匹配开始或者前后移动匹配成功了。
        // j 代表匹配了多少个相同的组块了。
        if (j == 0 || t[i] == t[j]) {
            ++i, ++j;
            next[i] = j;
            // 填充 next 数组
        } else {
            j = next[j];
            // 这里是 i 不变，j 变成 next[j]，即让模式串右移一位
        }
    }
}

int KMP(string s, string t,int pos)
{
    // 获取 Next 数组
    int *next= new int[t.size()];
    GetNext(t, next);
    // i 是主串 s 当前匹配到的位置，j 是子串 t 当前匹配到的位置
    int i = pos, j = 0;
    while (i < s.length() && j < t.length()) {
        // 如果 j == 0 或者当前字符匹配成功（即 s[i] == t[j]），都令 i++，j++，继续匹配下一个字符
        if (j == 0 || s[i] == t[j]) {
            ++i, ++j;
        } else {
            j = next[j];
        }
    }
    delete [] next;

    // 如果 j 超出了 t 的长度，说明匹配成功
    if (j > t.length()) return i - t.length();
    else return 0;
}



int main(){
    string s,t;

    // 输入一下
    getline(cin,s);
    getline(cin,t);
    
    // 定位子串的位置
    int pos=KMP(s,t,0);
    if (pos) cout << "Location: " << pos << endl;
    else cout << "Location: not found!" << endl;
    return 0;
}

