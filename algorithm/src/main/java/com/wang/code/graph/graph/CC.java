package com.wang.code.graph.graph;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/24
 */
public class CC {

    private int[] id;
    private boolean[] marked;
    private int count;

    public CC(Graph g, int s){
        id = new int[g.V];
        marked = new boolean[g.V];
        for (int i = 0; i < g.V; i++){
            if (!marked[i]){
                dfs(g, s);
                count++;
            }
        }

    }

    public void dfs(Graph g, int v){
        id[v] = count;
        marked[v] = true;
        for (int k: g.adj(v)) {
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
