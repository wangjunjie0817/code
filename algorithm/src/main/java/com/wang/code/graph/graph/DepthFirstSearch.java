package com.wang.code.graph.graph;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/23
 */
public class DepthFirstSearch {

    public boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V];
        dfs(G, s);
    }

    public void dfs(Graph G, int v){
        marked[v] = true;
        System.out.println(v);
        for (int k: G.adj(v) ) {
            if (!marked(k)){
                dfs(G, k);
            }
        }
    }

    public boolean marked(int w){
        return marked[w];
    }

    public int count(){
        return count;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0, 5);
        graph.addEdge(4, 3);
        graph.addEdge(0, 1);
        graph.addEdge(9, 12);
        graph.addEdge(6, 7);
        graph.addEdge(5, 4);
        graph.addEdge(0, 2);
        graph.addEdge(11, 12);
        graph.addEdge(9, 10);
        graph.addEdge(0, 6);
        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(5, 3);

        DepthFirstSearch search = new DepthFirstSearch(graph, 0);


    }

}
