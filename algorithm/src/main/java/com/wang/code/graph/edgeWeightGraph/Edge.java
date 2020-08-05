package com.wang.code.graph.edgeWeightGraph;

/**
 * @author WANGJJ
 * @date 2020/04/26
 */
public class Edge implements Comparable<Edge> {

    private int either;
    private int other;
    private double weight;

    public Edge(int v, int w, double weight){
        this.either = v;
        this.other = w;
        this.weight = weight;
    }

    public int either(){
        return either;
    }

    public double weight(){
        return weight;
    }

    public int other(int v){
        if (v == either) {return other;}
        if (v == other) {return either;}
        throw new RuntimeException("invalid v");
    }

    @Override
    public int compareTo(Edge e) {
        return Double.compare(this.weight, e.weight);
    }
}
