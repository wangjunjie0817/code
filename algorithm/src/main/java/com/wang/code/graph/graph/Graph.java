package com.wang.code.graph.graph;

import com.wang.code.one.Bag;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/23
 */
public class Graph {

    public int V;
    public int E;
    public Bag<Integer>[] adj;

    public Graph(int v){
        this.V = v;
        this.E = 0;
        adj = new Bag[V];
        for (int i = 0; i < v; i++){
            adj[i] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

}
