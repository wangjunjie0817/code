package com.wang.code.graph.graph;

import com.wang.code.one.Stack;

/**
 * @author WANGJJ
 * @date 2020/04/27
 */
public class DepthFirstPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstPaths(Graph g, int s){
        this.marked = new boolean[g.V];
        this.edgeTo = new int[g.V];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        for (int w:g.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }

        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<Integer> stack = new Stack<>(20);
        for (int w = v; w!= s; w = edgeTo[w]){
            stack.push(w);
        }
        stack.push(s);
        return stack;
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

        DepthFirstPaths path = new DepthFirstPaths(graph, 0);
        for (int w: path.pathTo(8)){
            System.out.println(w);
        }


    }

}
