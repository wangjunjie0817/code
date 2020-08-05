package com.wang.code.graph.edgeWeightGraph;

import com.wang.code.one.Bag;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/26
 */
public class EdgeWeightGraph {

    private int V;
    private int E;
    private Bag<Edge>[] bag;

    public EdgeWeightGraph(int v){
        this.V = v;
        bag = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < v; i++){
            bag[i] = new Bag<>();
        }
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        bag[v].add(e);
        bag[w].add(e);
        E++;
    }

    public int v(){
        return V;
    }

    public int e(){
        return E;
    }

    public Iterable<Edge> adj(int v){
        return bag[v];
    }

    Iterable<Edge> edges(){
        Bag<Edge> edges = new Bag<Edge>();
        for (int i = 0; i < V; i++){
            for (Edge e : adj(i)){
                if (e.other(i) > i){
                    edges.add(e);
                }
            }
        }
        return edges;
    }



}
