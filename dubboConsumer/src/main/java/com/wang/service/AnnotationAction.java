package com.wang.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.support.RpcUtils;
import org.springframework.stereotype.Component;

/**
 * @author WANGJJ
 * @date 2020/07/20
 */

@Component("annotationAction")
public class AnnotationAction {

    @Reference(cluster = "random")
    AnnotationService annotationService;

    public String doSayHello(){
        String wangjunjie = annotationService.sayHello("wangjunjie");
        System.out.println(RpcContext.getContext().isConsumerSide());

        return wangjunjie;
    }

}
