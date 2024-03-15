#include <stdio.h>
#include <ctype.h> // 需要用到 isalnum 函数
#define MAX_VERTEX_NUM 100
#define INF 0x3f3f3f3f

// 邻接矩阵存储图
typedef struct {
    int vexNum, arcNum;
    int vertex[MAX_VERTEX_NUM]; // 记录每个顶点的信息（ASCII码）
    int arcs[MAX_VERTEX_NUM][MAX_VERTEX_NUM]; // 存图的邻接矩阵
} MGraph;

void createMGraph(MGraph* G) {
    scanf("%d %d", &G->vexNum, &G->arcNum);

    // 初始化邻接矩阵
    for (int i = 0; i < G->vexNum; i++) {
        for (int j = 0; j < G->vexNum; j++) {
            if (i == j)
                G->arcs[i][j] = 0;
            else
                G->arcs[i][j] = INF; // 初始时权值为INF
        }
    }
    getchar(); // 吸收换行符
    // 输入每个顶点的信息，过滤非数字/字母的输入
    char c;
    int count = 0;
    while (count < G->vexNum && (c = getchar()) != EOF) {
        if (isalnum(c)) {
            G->vertex[count++] = c;
        }
    }

    // 将顶点按ASCII码值从小到大排序
    for (int i = 0; i < G->vexNum-1; i++) {
        for (int j = i+1; j < G->vexNum; j++) {
            if (G->vertex[i] > G->vertex[j]) {
                // 交换两个顶点信息
                int tmp = G->vertex[i];
                G->vertex[i] = G->vertex[j];
                G->vertex[j] = tmp;

                // 对应地交换邻接矩阵中的行和列
                for (int k = 0; k < G->vexNum; k++) {
                    tmp = G->arcs[i][k];
                    G->arcs[i][k] = G->arcs[j][k];
                    G->arcs[j][k] = tmp;
                    tmp = G->arcs[k][i];
                    G->arcs[k][i] = G->arcs[k][j];
                    G->arcs[k][j] = tmp;
                }
            }
        }
    }

    // 输入每条边的信息
    int v1, v2, w;
    for (int i = 0; i < G->arcNum; i++) {
        getchar(); // 吸收换行符
        scanf("%d %d %d", &v1, &v2, &w);
        // 无向图
        G->arcs[v1-1][v2-1] = G->arcs[v2-1][v1-1] = w;
    }
}

void prim(MGraph* G) {
    int lowcost[MAX_VERTEX_NUM]; // 记录 U 到 V-U 的最短距离
    int closest[MAX_VERTEX_NUM]; // 记录每个 U 到 V-U 最近的顶点

    // 初始化 U 和 V-U
    int u = 0;
    const int MAX_VALUE = INF * G->vexNum; // 存储 int 类型最大值
    for (int v = 0; v < G->vexNum; v++) {
        if (v != u) {
            lowcost[v] = G->arcs[u][v];
            closest[v] = u;
        }
        else {
            lowcost[u] = 0;
        }
    }

    for (int i = 1; i <= G->vexNum-1; i++) {
        int k = -1, min = MAX_VALUE;

        // 在 V-U 中找到最小边
        for (int j = 0; j < G->vexNum; j++) {
            if (lowcost[j] != 0 && lowcost[j] < min) {
                min = lowcost[j];
                k = j;
            }
        }

        // 输出最小边所连接的两个顶点
        printf("(%c,%c)\n", G->vertex[closest[k]], G->vertex[k]);

        // 将从 V-U 移动到 U 的顶点（即k）加入 U 中
        lowcost[k] = 0;

        // 更新 U 到 V-U 的距离和 closest 数组
        for (int j = 0; j < G->vexNum; j++) {
            if (G->arcs[k][j] < lowcost[j]) {
                lowcost[j] = G->arcs[k][j];
                closest[j] = k;
            }
        }
    }
}

int main() {
    MGraph G;
    createMGraph(&G);
    prim(&G);

    return 0;
}