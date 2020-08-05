package com.wang.code.graph.graph;

/**
 * @author WANGJJ
 * @date 2020/04/23
 */
public class Search {

    private Graph G;
    private int s;

    public Search(Graph g, int s) {
        this.G = g;
        this.s = s;
    }

    // 判断节点和起点是否联通
    public boolean marked(int v) {
        return false;
    }

    // 与起点s联通的顶点数量
    public int count() {
        return 1;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(13);
        graph.addEdge(0, 5);
        graph.addEdge(4, 3);
        graph.addEdge(0, 1);
        graph.addEdge(9, 12);
        graph.addEdge(6, 4);
        graph.addEdge(5, 4);
        graph.addEdge(0, 2);
        graph.addEdge(11, 12);
        graph.addEdge(9, 10);
        graph.addEdge(0, 6);
        graph.addEdge(7, 8);
        graph.addEdge(9, 11);
        graph.addEdge(5, 3);

        Search search = new Search(graph, 0);


    }

}
