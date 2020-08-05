package com.wang.code.graph.edgeWeightDigraph;

/**
 * @author WANGJJ
 * @date 2020/04/26
 */
public class DirectEdge {

    public int v;
    public int w;
    public double weight;

    public DirectEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public double weight() {
        return weight;
    }
}
