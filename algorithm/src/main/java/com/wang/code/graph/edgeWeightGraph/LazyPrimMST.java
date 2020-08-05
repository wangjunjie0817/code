package com.wang.code.graph.edgeWeightGraph;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author WANGJJ
 * @date 2020/04/26
 */
public class LazyPrimMST {

    private boolean[] marked;
    private LinkedBlockingQueue<Edge> MST;
    private PriorityQueue<Edge> priorityQueue;

    public LazyPrimMST(EdgeWeightGraph graph){
        marked = new boolean[graph.v()];
        MST = new LinkedBlockingQueue<>();
        priorityQueue = new PriorityQueue<>();
        visit(graph, 0);
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            int v = edge.either();
            int w = edge.other(v);
            if (marked[v] && marked[w]){
                continue;
            }
            MST.add(edge);
            if (!marked[v]){
                visit(graph, v);
            }
            if (!marked[w]){
                visit(graph, w);
            }
        }
    }

    public void visit(EdgeWeightGraph g, int v){
        marked[v] = true;
        for (Edge e: g.adj(v)) {
            if (!marked[e.other(v)]){
                priorityQueue.add(e);
            }
        }
    }

    public Iterable<Edge> edges(){
        return MST;
    }

}
