package com.ping.wu.jraft.counter.rpc;

import com.alipay.sofa.jraft.rpc.RpcContext;
import com.alipay.sofa.jraft.rpc.RpcProcessor;

/**
 * @author wuping
 * @date 2020-05-26
 */

public class GetValueRequestProcessor implements RpcProcessor<GetValueRequest> {
    @Override
    public void handleRequest(RpcContext rpcContext, GetValueRequest getValueRequest) {

    }

    @Override
    public String interest() {
        return GetValueRequest.class.getName();
    }
}
