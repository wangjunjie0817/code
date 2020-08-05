package com.wang.code.graph.diGraph;

import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author WANGJJ
 * @date 2020/04/24
 */
public class DirectDFS {

    private boolean[] marked;

    LinkedBlockingQueue<Integer> pre;
    LinkedBlockingQueue<Integer> post;
    Stack<Integer> reversePost;

    public DirectDFS(DiGraph g, int s) throws InterruptedException {
        pre = new LinkedBlockingQueue<>();
        post = new LinkedBlockingQueue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.V];
        dfs(g, s);
        System.out.println("a");
    }

    public void dfs(DiGraph g, int s) throws InterruptedException {
        marked[s] = true;
        pre.put(s);
        Iterator<Integer> it = g.adj(s);
        while (it.hasNext()){
            int v = it.next();
            if (!marked[v]){
                dfs(g, v);
            }
        }
        post.put(s);
        reversePost.push(s);
    }

    public Stack<Integer> reversePost(){
        return this.reversePost;
    }

    public boolean marked(int v){
        return marked[v];
    }

}
