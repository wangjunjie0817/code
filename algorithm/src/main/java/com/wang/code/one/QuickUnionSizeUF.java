package com.wang.code.one;

/**
 * @author WANGJJ
 * @date 2020/04/27
 */
public class QuickUnionSizeUF {

    private int[] id;
    private int count;
    private int[] size;

    // N的概念是一开始我们有N个连通分量，通过union方法后，N的数量-1
    public QuickUnionSizeUF(int N) {
        count = N;

        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            // 一开始所有点的链接都指向自己
            id[i] = i;
            size[i] = 1;
        }
    }

    // 将两个连通分量合并
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        int sizePID = size[pID];
        int sizeQID = size[qID];
        if (pID == qID){
            return;
        }
        if (sizePID < sizeQID) {
            id[pID] = qID;
            size[qID] = sizeQID + sizePID;
        } else {
            id[qID] = pID;
            size[pID] = sizePID + sizeQID;
        }
        count--;
    }

    // p点所在的连通分量的标识符
    public int find(int p) {
        while (p != id[p]){
            p = id[p];
        }
        return p;
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
        QuickUnionSizeUF uf = new QuickUnionSizeUF(10);
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
        System.out.println(uf.count());
    }

}
