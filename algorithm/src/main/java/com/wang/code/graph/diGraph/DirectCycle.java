package com.wang.code.graph.diGraph;

import com.wang.code.one.Stack;

import java.util.Iterator;

/**
 * @author WANGJJ
 * @date 2020/04/24
 */
public class DirectCycle {

    private boolean[] marked;
    private boolean[] onStack;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public DirectCycle(DiGraph graph, int s){
        marked = new boolean[graph.V];
        onStack = new boolean[graph.V];
        edgeTo = new int[graph.V];
        dfs(graph, s);
    }

    private void dfs(DiGraph graph, int v){
        marked[v] = true;
        onStack[v] = true;
        Iterator<Integer> it = graph.adj(v);
        while (it.hasNext()){
            int w = it.next();
            if (this.hasCycle()){
                return;
            }
            else if (!marked[w]){
                edgeTo[w] = v;
                dfs(graph, w);
            }
            else if (onStack[w]){
                cycle = new Stack<>(20);
                // 这个顺序要参照图来看能看懂
                for (int i = v; i != w; i = edgeTo[i]){
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        DiGraph diGraph = new DiGraph(6);
        diGraph.addEdge(0, 5);
        diGraph.addEdge(5, 4);
        diGraph.addEdge(4, 3);
        diGraph.addEdge(3, 5);
        DirectCycle directCycle = new DirectCycle(diGraph, 0);
        System.out.println(directCycle.hasCycle());

        for (int a: directCycle.cycle()){
            System.out.println(a);
        }
    }

}
