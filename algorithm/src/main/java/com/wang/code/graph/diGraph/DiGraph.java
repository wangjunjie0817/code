package com.wang.code.graph.diGraph;

import com.wang.code.one.Bag;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/24
 */
public class DiGraph {

    public int V;
    public int E;
    public Bag<Integer>[] adj;

    public DiGraph(int v){
        this.V = v;
        this.E = 0;
        adj = new Bag[V];
        for (int i = 0; i < v; i++){
            adj[i] = new Bag<Integer>();
        }
    }

    public int v(){
        return V;
    }

    public int e(){
        return E;
    }

    public DiGraph reverse(DiGraph graph){
        DiGraph diGraph = new DiGraph(graph.V);
        for (int i = 0; i < graph.V; i++){
            Iterator<Integer> it = graph.adj(i);
            while (it.hasNext()){
                int w = it.next();
                diGraph.addEdge(w, i);
            }
        }
        return diGraph;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }

    public Iterator<Integer> adj(int v){
        return adj[v].iterator();
    }
}
