#include <iostream>
using namespace std;

// 边表结点
struct ArcNode {
    int adjvex;         // 邻接点在顶点数组中的下标
    struct ArcNode *nextarc; // 指向下一个边表结点
};

// 顶点表头结点
struct VNode {
    char data;          // 顶点数据
    ArcNode *firstarc;   // 指向第一个边表结点
};

// 图
struct UDG {
    VNode vertices[100]; // 顶点表
    int vexnum, arcnum;  // 顶点数，边数
};

//-------func

// 获取顶点 v 在顶点表中的下标
int LocateVex(UDG G, char v) {
    for (int i = 0; i < G.vexnum; i++) {
        if (G.vertices[i].data == v) {
            return i;
        }
    }
    return -1;
}

// 创建无向图
void CreateUDG(UDG &G) {
    // 输入邻接表的顶点数和边数，方便后续遍历
    cin >> G.vexnum >> G.arcnum;
    // 输入顶点信息
    for (int i = 0; i < G.vexnum; i++) {
        cin >> G.vertices[i].data;
        G.vertices[i].firstarc = NULL; 
        // 初始化的边表都是空
    }
    // 遍历边数，构造关系边表
    for (int k = 0; k < G.arcnum; k++) {
        char v, w;
        // 输入 ： A B 来创建关系。
        cin >> v >> w;
        int i = LocateVex(G, v);  // 获取顶点 v 在顶点表中的下标
        int j = LocateVex(G, w);  // 获取顶点 w 在顶点表中的下标

        // 将新结点插入到边表头部
        ArcNode *p = new ArcNode;
        p->adjvex = j;  
        // 邻接点在顶点数组中的下标
        p->nextarc = G.vertices[i].firstarc;
        // 连接到对应的顶点的边表头部
        G.vertices[i].firstarc = p; 
        
        // 将新结点插入到边表头部
        p = new ArcNode;
        p->adjvex = i;
        // 我们先让新结点的 nextarc 指向顶点 i 的边表头部
        p->nextarc = G.vertices[j].firstarc;
        // 然后再让顶点 j 的边表头部指向新结点！
        G.vertices[j].firstarc = p;
    }
}

// 删除边 <v,w>
// 注解：邻接表的删除操作，其实就是遍历顶点的边表，删除边表的结点，这样来解除它们的关系
void DeleteArc(UDG &G, char v, char w) {
    // 定位顶点的下标，因为我们需要在边表里面查找。

    int i = LocateVex(G, v);
    int j = LocateVex(G, w);

    // 这里寻找边
    ArcNode *p = G.vertices[i].firstarc, *q = NULL;

    // 遍历，查边表，比对下标，找前驱
    while (p != NULL && p->adjvex != j) {
        q = p;
        p = p->nextarc;
    }
    // 如果没找到，p 会是 NULL，如果找到了，证明 i 和 j 一定存在
    if (p != NULL) { 
        // 这里我们要解决一个特殊情况：第一个
        if (q == NULL) { 
            // 前驱无，要删除的是第一个结点（边表）
            G.vertices[i].firstarc = p->nextarc;
        } else {
            q->nextarc = p->nextarc;
        }
        delete p; 
        // 释放空间

        // 删除另一条边，重复上面的操作
        p = G.vertices[j].firstarc;
        q = NULL;
        while (p != NULL && p->adjvex != i) {
            q = p;
            p = p->nextarc;
        }
        if (q == NULL) {
            G.vertices[j].firstarc = p->nextarc;
        } else {
            q->nextarc = p->nextarc;
        }
        delete p;
        // 删除完毕。
    } else { 
        // 没有找到该边
        cout << "no such edge!" << endl;
    }
}

// 输出邻接表
void PrintUDG(UDG G) {
    // 这里包含：打印下标，打印下标顶点对应的数据，while遍历链表。
    for (int i = 0; i < G.vexnum; i++) {
        cout << " " << i << "   " << G.vertices[i].data << ": ";
        ArcNode *p = G.vertices[i].firstarc;
        while (p != NULL) {
            cout << p->adjvex << " ";
            p = p->nextarc;
        }
        cout << endl;
    }
}

int main() {
    // 创建无向图
    UDG G;
    CreateUDG(G);
    char v, w;
    cin >> v >> w;
    // 删除边 <v,w>
    DeleteArc(G, v, w);
    // 打印邻接表
    PrintUDG(G);
    return 0;
}
