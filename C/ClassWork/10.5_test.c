//@等待优化设计的测验题

#include < stdio.h >

/*
    要求是 30个人！所以要符合 sum=30；
    那么我们通过暴力遍历可以得出，可是有没有更好的算法呢？
    3x+2y+z=50;
    x+y+z=30;
    y+z+x-30;
    遍历x穷举方程组。
*/

struct Ca_result {
        int girl;
        int child;
    };

void girl_child(int money, int person, int man, struct Ca_result & result) {
    int child = 0, girl = 0;
    // 计算剩余
    money -= man * 3;
    person -= man;
    // 遍历分配,这里代表女人 
    for (int i = 0; i <= person; i++) {
        if ((i * 2 + (person - i) * 1) == money) {
            girl = i;
            child = person - girl;
        }
    }
    result.girl = girl;
    result.child = child;
}

int test5() {
    int money = 50, person = 30, child, girl;
    struct Ca_result result;
    for (int man = 1; man < 30; man++) {
        girl_child(money, person, man, result);
        //printf("男：%d 女：%d 孩：%d",man,result.girl,result.child);
        if (result.girl > 0 && result.child > 0) {
            printf("男%3d 女%3d 孩%3d\n", man, result.girl, result.child);
        }
    }
}

int main() {

    test5();

}


int test6() {
    int bai, shi, ge, line = 0;
    for (int i = 1; i < 5; ++i)
        for (int j = 1; j < 5; ++j)
    for (int k = 1; k < 5; ++k) {
        if (i == k || k == j || j == i)
            continue;
        if (line < 3) {
            printf("%6d", i * 100 + j * 10 + k);
            line++;
        } else {
            printf("%6d\n", i * 100 + j * 10 + k);
            line = 0;
        }
    }
}

//--------------------------

int su_num(int input) {
    if (input < 2) {
        return 0;
    }
    for (int i = 2; i < input; i++) {
        if (input % i == 0) {
            return 0;
        }
    }
    return 1;
}


int test4() {
    int out, line_turn = 1;
    for (int i = 0; i <= 100; i++) {
        if (su_num(i)) {
            if (line_turn < 5) {
                printf("%6d", i);
                ++line_turn;
            } else {
                printf("%6d\n", i);
                line_turn = 1;
            }
        }
    }
}