// 冒泡排序
void Sort(int Num[]) {
    //O(n^n)
    for (int k=0; k<sizeof(Num)+2-1; k++) {
        // 一次排序
        for (int i=0; i<sizeof(Num)+2-k-1; i++) {
            if (Num[i]>Num[i+1]) {
                int temp=Num[i+1];
                Num[i+1]=Num[i];
                Num[i]=temp;
            }
        }
    }
}

