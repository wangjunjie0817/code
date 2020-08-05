package com.wang.code.graph.edgeWeightGraph;

import com.wang.code.sort.top.IndexMinPQ;

import java.util.PriorityQueue;

/**
 * @author WANGJJ
 * @date 2020/04/26
 */
public class PrimMST {

    public Edge[] edgeTo;
    public double[] distTo;
    public boolean[] marked;
    private IndexMinPQ<Double> queue;

    public PrimMST(EdgeWeightGraph graph){
        edgeTo = new Edge[graph.v()];
        distTo = new double[graph.v()];
        marked = new boolean[graph.v()];
        queue = new IndexMinPQ<>(50);
        for (int i = 0; i < graph.v(); i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0.0;
        queue.insert(0, 0.0);
        while (!queue.isEmpty()){
            int i = queue.delMin();
            visit(graph, i);
        }
    }

    public void visit(EdgeWeightGraph graph, int v){
        marked[v] = true;
        for (Edge e: graph.adj(v)){
            int w = e.other(v);
            if (marked[w]){
                continue;
            }
            if (e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (queue.contains(w)){
                    queue.changeKey(w, distTo[w]);
                } else {
                    queue.insert(w, distTo[w]);
                }
            }
        }
    }

}
