package com.wang.code.one;

/**
 * @author WANGJJ
 * @date 2020/04/27
 */
public class QuickFindUF {

    private int[] id;
    private int count;

    // N的概念是一开始我们有N个连通分量，通过union方法后，N的数量-1
    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // 将两个连通分量合并
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID){
            return;
        }
        for (int i = 0; i < id.length; i++){
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
        count--;
    }

    // p点所在的连通分量的标识符
    public int find(int p) {
        return id[p];
    }

    // 判断两个点是否在一个连通分量中
    public boolean connect(int p, int q) {
        return find(p) == find(q);
    }

    // 连通分量数
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        QuickFindUF uf = new QuickFindUF(10);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(8, 9);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);
        uf.union(6, 7);
        System.out.println(uf.count);
    }
}
