package com.wang.code.graph.edgeWeightGraph;

import com.wang.code.one.QuickFindUF;
import com.wang.code.sort.top.MinPQ;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author WANGJJ
 * @date 2020/04/28
 */
public class KruskalMST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightGraph graph){
        mst = new LinkedBlockingQueue<>();
        MinPQ<Edge> pq = new MinPQ<>(graph.e());
        QuickFindUF uf = new QuickFindUF(graph.e());
        for (Edge e:graph.edges()){
            pq.insert(e);
        }
        while (!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connect(v, w)){
                continue;
            }
            uf.union(v, w);
            mst.add(e);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

}
