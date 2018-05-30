package xyz.kemix.dubbo.demo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcContext;

/**
 * @author Kemix Koo
 *
 */
public abstract class AbsConsumer {
    /*
     * FIXME, default will be dubbo://...., but if don't match provider, will have timeout error.
     */
    // protected String address = "127.0.0.1:2181";
    protected String address = "zookeeper://127.0.0.1:2181"; // must same as provider

    protected ApplicationConfig createApp(String name) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName(name);
        return application;
    }

    protected RegistryConfig createRegistry() {
        // add registry addresses
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(address);

        return registry;
    }

    protected void printServer() {
        final String serverIP = RpcContext.getContext().getRemoteHost();
        final String applicationName = RpcContext.getContext().getUrl().getParameter("application");
        System.out.println("服务器:" + serverIP);
        System.out.println("应用名称:" + applicationName);
        System.out.println();
    }

    public abstract void getPermission();

    public abstract void sayHello();

}
