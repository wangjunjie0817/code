package com.wang.code.graph.diGraph;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/25
 * 算法说明：检测有向图中的强连通行
 * 实际上理解起来比较简单，有向图的反向图如果 w -> v是联通的，那必然对于两个点是强连通的
 */
public class KosaraCC {

    private int[] id;
    private boolean[] marked;
    private int count;

    public KosaraCC(DiGraph g, int s) throws InterruptedException {
        id = new int[g.V];
        marked = new boolean[g.V];
        DiGraph reverseGraph = g.reverse(g);
        DirectDFS directDFS = new DirectDFS(reverseGraph, 0);
        for (int i: directDFS.reversePost){
            if (!marked[i]){
                dfs(g, s);
                count++;
            }
        }

    }

    public void dfs(DiGraph g, int v){
        id[v] = count;
        marked[v] = true;
        for (Iterator<Integer> it = g.adj(v); it.hasNext(); ) {
            int k = it.next();
            if (!marked[k]){
                dfs(g, k);
            }
        }
    }

    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    public int count(){
        return count;
    }

    public int id(int v){
        return id[v];
    }

}
