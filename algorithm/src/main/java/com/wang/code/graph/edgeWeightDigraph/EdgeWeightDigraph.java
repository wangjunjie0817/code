package com.wang.code.graph.edgeWeightDigraph;

import com.wang.code.graph.edgeWeightGraph.Edge;
import com.wang.code.one.Bag;

/**
 * @author WANGJJ
 * @date 2020/04/26
 */
public class EdgeWeightDigraph {

    public int V;
    public int E;
    public Bag<DirectEdge>[] adj;

    public EdgeWeightDigraph(int v){
        this.V = v;
        this.E = 0;
        adj = (Bag<DirectEdge>[]) new Bag[v];
        for(int i = 0; i < v; i++){
            adj[i] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DirectEdge> adj(int v){
        return adj[v];
    }

    public void addEdge(DirectEdge edge){
        E++;
        adj[edge.from()].add(edge);
    }

    public Iterable<DirectEdge> edges(){
        Bag<DirectEdge> bag = new Bag<>();
        for (int i=0; i < V; i++){
            for (DirectEdge edge: adj(i)){
                bag.add(edge);
            }
        }
        return bag;
    }
}
