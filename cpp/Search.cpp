// 图的广度优先遍历BFS算法是一个分层搜索的过程，和树的层序遍历算法类同，它也需要一个队列以保持遍历过的顶点顺序，以便按出队的顺序再去访问这些顶点的邻接顶点。 

/*
设计一个程序，依次从有向图中的每个顶点广度优先搜索遍历操作；邻接矩阵作为存储结构。
输入样例：
4 4
1 2 3 4
1 2
1 3
3 4
4 1

输出n行，每行输出从第i(0≤i＜n)个顶点广度优先搜索的访问序列，序列中的连通分量用{}括起来，无空格间隔，{}为英文半角的符号
输出样例：
{1234}
{2}{341}
{3412}
{4123}
*/

#include<bits/stdc++.h> //万能头
#include <iostream>
#include <queue>
using namespace std;

#define MAX_VERTEX_NUM 100
#define INFINITY 65535

// 顶点类型
typedef char VertexType;
// 边上的权值类型
typedef int EdgeType;
// 定义图的结构体
typedef struct
{
    // 顶点表
    VertexType vexs[MAX_VERTEX_NUM];
    // 邻接矩阵
    EdgeType arc[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
    // 顶点数、边数
    int vexnum, arcnum;
} MGraph;

// 返回顶点v在有向图中的位置，若图中不存在此顶点返回-1
int LocateVex(MGraph G, VertexType v)
{
    for (int i = 0; i < G.vexnum; i++)
    {
        if (G.vexs[i] == v)
            return i;
    }
    return -1;
}

// 构造有向图G
void CreateDG(MGraph &G)
{
    cout << "请输入有向图的顶点数和边数：";
    cin >> G.vexnum >> G.arcnum;
    cout << "请输入" << G.vexnum << "个顶点：";
    // 输入顶点信息，建立顶点表
    for (int i = 0; i < G.vexnum; i++)
    {
        cin >> G.vexs[i];
    }
    // 初始化邻接矩阵，边的权值均为INFINITY，即不相邻接
    for (int i = 0; i < G.vexnum; i++)
    {
        for (int j = 0; j < G.vexnum; j++)
        {
            G.arc[i][j] = INFINITY;
        }
    }
    // 构造邻接矩阵，赋值为1的表示相邻接
    for (int k = 0; k < G.arcnum; k++)
    {
        VertexType v1, v2;
        cout << "请输入边(vi,vj)的顶点v1和v2：";
        cin >> v1 >> v2;
        int i = LocateVex(G, v1);
        int j = LocateVex(G, v2);
        G.arc[i][j] = 1;
    }
}

// 访问一个节点v
void visit(VertexType v)
{
    cout << v;
}

// 从第v个节点开始，广度优先遍历图G
void BFS(MGraph G, int v, bool visited[])
{
    // 定义一个队列
    queue<int> Q;
    // 访问该节点v，并将其标记已访问过
    visit(G.vexs[v]);
    visited[v] = true;
    // 节点v入队列Q
    Q.push(v);
    while (!Q.empty())
    {
        // 队头元素出队列Q
        int u = Q.front();
        Q.pop();
        // 依次访问u的所有邻接点，其实就是访问u所在行的所有元素
        for (int w = 0; w < G.vexnum; w++)
        {
            // 判定是否邻接点且未访问过
            if (w != u && G.arc[u][w] != INFINITY && !visited[w])
            {
                // 访问w并标记已访问过
                visit(G.vexs[w]);
                visited[w] = true;
                // 节点w入队列Q
                Q.push(w);
            }
        }
    }
}

// 对图进行广度优先遍历，即对每个节点进行BFS操作
void BFSTraverse(MGraph G)
{
    for (int r = 0; r < G.vexnum; r++)
    {
        bool visited[MAX_VERTEX_NUM] = {false};
        // 对每个节点进行BFS操作
        for (int i = r; i < G.vexnum; i++)
        {
            if (!visited[i])
            {
                cout << "{";
                BFS(G, i, visited);
                cout << "}" << endl;
            }
        }
    }
}

int main()
{
    MGraph G;
    CreateDG(G);
    BFSTraverse(G);
    return 0;
}
