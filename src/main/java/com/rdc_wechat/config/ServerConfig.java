package com.rdc_wechat.config;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;
import java.util.Set;

/**
 * 项目启动时会自动启动，类似与ContextListener.
 *  是webSocket的核心配置类。
 * @author 86178
 */
public class ServerConfig implements ServerApplicationConfig {

    /**
     * 扫描src下所有类@ServerEndPoint注解的类。
     */
    @Override
    public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scan) {
        System.out.println("扫描到"+scan.size()+"个服务端程序");
        return scan;
    }

    @Override
    public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> point) {
        System.out.println("实现EndPoint接口的类数量："+point.size());
        return null;
    }
}
