package xyz.kemix.dubbo.demo;

import java.util.List;
import java.util.UUID;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * @author Kemix Koo
 *
 *         more details: http://dubbo.incubator.apache.org/books/dubbo-user-book/demos/generic-reference.html
 */
public class GenericConsumer {
    @SuppressWarnings({ "unchecked" })
    public static void invokePermissions() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("perm-" + UUID.randomUUID().toString());

        String interfaceName = "xyz.kemix.dubbo.demo.service.PermissionService";
        /*
         * FIXME, default will be dubbo://...., but if don't match provider, will have timeout error.
         */
        // String address = "127.0.0.1:2181";
        String address = "zookeeper://127.0.0.1:2181"; // must same as provider

        // add registry addresses
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(address);

        //
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setGeneric(true);

        reference.setApplication(application);
        reference.setRegistry(registry);
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
        Object result = genericService.$invoke("getPermissions", new String[] { Long.class.getName() }, new Object[] { 2L });

        if (result instanceof List) {
            ((List<String>) result).forEach(System.out::println);
        }

        /*
         * FIXME, If can't match, will try via the setting retries and throw com.alibaba.dubbo.remoting.TimeoutException
         */
        // result = genericService.$invoke("getPermissions", new String[] { int.class.getName() }, new Object[] { 2L });

    }

    public static void invokeHello() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("hello-" + UUID.randomUUID().toString());

        String interfaceName = "xyz.kemix.dubbo.demo.service.HelloService";
        String address = "127.0.0.1:2181";

        // add registry addresses
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(address);

        //
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(interfaceName);
        reference.setConnections(10);
        reference.setTimeout(1000);
        reference.setVersion("*"); // no special version
        reference.setGeneric(true);

        GenericService genericService = reference.get();
        Object result = genericService.$invoke("sayHello", new String[] { String.class.getName() }, new Object[] { "world" });

        System.out.println(result);

    }

    public static void main(String[] args) {
        invokePermissions();
        invokeHello();
    }

}
