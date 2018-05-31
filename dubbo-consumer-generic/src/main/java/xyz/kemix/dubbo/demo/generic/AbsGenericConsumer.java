package xyz.kemix.dubbo.demo.generic;

import java.util.List;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * @author Kemix Koo
 *
 *         more details: http://dubbo.incubator.apache.org/books/dubbo-user-book/demos/generic-reference.html
 */
public abstract class AbsGenericConsumer extends AbsConsumer {

    @SuppressWarnings({ "unchecked" })
    public void getPermissions() {
        String interfaceName = "xyz.kemix.dubbo.demo.service.PermissionService";
        String methodName = "getPermissions";

        // add registry addresses
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(address);

        //
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setGeneric(true);

        reference.setApplication(createApp("gen-perm"));
        reference.setRegistry(createRegistry());
        reference.setInterface(interfaceName);
        reference.setConnections(10);
        reference.setTimeout(1000);
        /*
         * FIXME, If the provider don't set version, also no need set, else can't match. if have multi-versions, can set * for all.
         */
        // reference.setVersion("*");

        //
        // MethodConfig methodConfig = new MethodConfig();
        // methodConfig.setName(methodName);
        // methodConfig.setTimeout(timeout);
        // reference.setMethods(Arrays.asList(methodConfig));
        //
        // //
        // ConsumerConfig consumerConfig = new ConsumerConfig();
        // consumerConfig.setTimeout(timeout);
        // reference.setConsumer(consumerConfig);

        GenericService genericService = reference.get();
        Object result = genericService.$invoke(methodName, new String[] { Long.class.getName() }, new Object[] { 2L });

        System.out.println(methodName + "返回结果:");
        if (result instanceof List) {
            ((List<String>) result).forEach(System.out::println);
        }
        printServer();

        /*
         * FIXME, If can't match, will try via the setting retries and throw com.alibaba.dubbo.remoting.TimeoutException
         */
        // result = genericService.$invoke("getPermissions", new String[] { int.class.getName() }, new Object[] { 2L });

    }

    /**
     * if don't set the HelloService in provider, because can't find it, will have timeout error
     */
    public void sayHello() {
        String interfaceName = "xyz.kemix.dubbo.demo.service.HelloService";
        String methodName = "sayHello";
        //
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setGeneric(true);

        reference.setApplication(createApp("gen-hello"));
        reference.setRegistry(createRegistry());
        reference.setInterface(interfaceName);
        reference.setConnections(10);
        reference.setTimeout(1000);
        /*
         * FIXME, If the provider don't set version, also no need set, else can't match. if have multi-versions, can set * for all.
         */
        // reference.setVersion("*");

        GenericService genericService = reference.get();
        Object result = genericService.$invoke(methodName, new String[] { String.class.getName() }, new Object[] { "world" });

        System.out.println(methodName + "返回结果: " + result);
        printServer();
    }

}
