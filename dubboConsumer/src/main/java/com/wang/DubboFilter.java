package com.wang;

import com.alibaba.dubbo.rpc.*;

/**
 * @author WANGJJ
 * @date 2020/07/22
 */
public class DubboFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        System.out.println("dubbo请求之前");

        Result result = invoker.invoke(invocation);

        System.out.println("dubbo请求之后");

        return result;
    }
}
