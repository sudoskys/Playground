#include <iostream>
#define MAX_VERTEX_NUM 100
using namespace std;

typedef struct
{
    char vexs[MAX_VERTEX_NUM];                // 存储顶点的一维数组
    int arcs[MAX_VERTEX_NUM][MAX_VERTEX_NUM]; // 存储邻接矩阵的二维数组
    int vexnum, arcnum;                       // 记录图的当前顶点数和边数
} MGraph;

int LocateVex(MGraph G, char v)
{ // 利用v在图G中查找顶点的位置，如果不存在则返回-1
    for (int i = 0; i < G.vexnum; ++i)
    {
        if (G.vexs[i] == v)
        {
            return i;
        }
    }
    return -1;
}

void CreateUDG(MGraph &G)
{ // 创建无向图，采用邻接矩阵表示
    cin >> G.vexnum >> G.arcnum;
    for (int i = 0; i < G.vexnum; ++i)
    {
        cin >> G.vexs[i];
    }
    for (int i = 0; i < G.vexnum; ++i)
    {
        for (int j = 0; j < G.vexnum; ++j)
        {
            G.arcs[i][j] = 0;
        }
    }
    for (int k = 0; k < G.arcnum; ++k)
    {
        char v1, v2;
        cin >> v1 >> v2;
        int i = LocateVex(G, v1);
        int j = LocateVex(G, v2);
        G.arcs[i][j] = 1;
        G.arcs[j][i] = G.arcs[i][j]; // 无向图的邻接矩阵是对称矩阵
    }
}

void InsertVex(MGraph &G, char v)
{ // 在无向图G中新增一个顶点v
    G.vexs[G.vexnum++] = v;
}

void PrintUDG(MGraph G)
{ // 输出无向图G的邻接矩阵
    for (int i = 0; i < G.vexnum; ++i)
    {
        for (int j = 0; j < G.vexnum; ++j)
        {
            cout << G.arcs[i][j] << " ";
        }
        cout << endl;
    }
}

int main()
{
    MGraph G;
    CreateUDG(G);
    char v;
    cin >> v;
    // 输入的是顶点的值，而不是顶点的位置
    InsertVex(G, v);
    PrintUDG(G);
    return 0;
}