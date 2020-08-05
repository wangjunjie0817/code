package com.wang.code.graph.edgeWeightDigraph;

import com.wang.code.one.Stack;
import com.wang.code.sort.top.IndexMinPQ;

/**
 * @author WANGJJ
 * @date 2020/04/28
 */
public class SP {

    private DirectEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public SP(EdgeWeightDigraph edgeWeightDigraph, int s){
        distTo = new double[edgeWeightDigraph.V];
        edgeTo = new DirectEdge[edgeWeightDigraph.V];
        pq = new IndexMinPQ<>(edgeWeightDigraph.V());
        for (int i = 0; i < edgeWeightDigraph.V; i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0.0;
        edgeTo[0] = null;
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectEdge> pathTo(int v){
        Stack<DirectEdge> edges = new Stack<>(edgeTo.length);
        for (DirectEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()]){
            edges.push(edge);
        }
        return edges;
    }

    public void relax(DirectEdge e){
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight){
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
        }
    }

    private void relax(EdgeWeightDigraph edgeWeightDigraph, int v){
        for (DirectEdge edge : edgeWeightDigraph.adj(v)) {
            int to = edge.to();
            if (distTo[to] > distTo[v] + edge.weight){
                distTo[to] = distTo[v] + edge.weight;
                edgeTo[to] = edge;
            }
        }
    }

}
