package com.wang.code.graph.diGraph;

/**
 * @author WANGJJ
 * @date 2020/04/27
 */
public class Topological {

    private Iterable<Integer> order;
    private boolean hasCycle;

    public Topological(DiGraph g) throws InterruptedException {
        DirectCycle cycle = new DirectCycle(g, 0);
        if (!cycle.hasCycle()){
            DirectDFS directDFS = new DirectDFS(g, 0);
            order = directDFS.reversePost();
        } else {
            hasCycle = true;
        }
    }

    // 当前有向图是否有环
    public boolean isDAG(){
        return hasCycle;
    }

    public Iterable<Integer> order(){
        return order;
    }

}
