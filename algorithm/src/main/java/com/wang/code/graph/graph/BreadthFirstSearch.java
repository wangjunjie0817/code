package com.wang.code.graph.graph;

import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author WANGJJ
 * @date 2020/04/24
 */
public class BreadthFirstSearch {

    public boolean[] marked;
    public int[] edgeTo;
    public int s;

    public BreadthFirstSearch(Graph G, int s) throws InterruptedException {
        edgeTo = new int[G.V];
        marked = new boolean[G.V];
        s = s;
        dfs(G, s);

    }

    public void dfs(Graph G, int v) throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        queue.put(v);
        while (!queue.isEmpty()){
            int w = queue.take();
            System.out.println(w);
            for (int k: G.adj(w) ) {
                if (!marked[k]){
                    edgeTo[k] = w;
                    marked[k] = true;
                    queue.put(k);
                }
            }
        }
    }

    public Iterable<Integer> pathTo(int v){
        if (!marked[v]){
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]){
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }

    public boolean marked(int w){
        return marked[w];
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public static void main(String[] args) throws InterruptedException {
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

        BreadthFirstSearch search = new BreadthFirstSearch(graph, 0);

        System.out.println();
        for (Integer a : search.pathTo(3)){
            System.out.println(a);
        }



    }

}
