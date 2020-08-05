package com.wang.code.graph.edgeWeightGraph;

/**
 * @author WANGJJ
 * @date 2020/04/26
 */
public class IndexDist implements Comparable<IndexDist> {

    private int v;
    private double dist;

    public IndexDist(int v, double dist){
        this.v = v;
        this.dist = dist;
    }


    @Override
    public int compareTo(IndexDist that) {
        if (this.dist > that.dist) {return 1;}
        else if (this.dist < that.dist) {return -1;}
        return 0;
    }

    public int v(){
        return v;
    }

    public double dist(){
        return dist;
    }
}
