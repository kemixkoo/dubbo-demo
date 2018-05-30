package xyz.kemix.dubbo.demo;

import java.util.List;
import java.util.UUID;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import xyz.kemix.dubbo.demo.service.HelloService;
import xyz.kemix.dubbo.demo.service.PermissionService;

/**
 * @author Kemix Koo
 *
 *         more details: http://dubbo.incubator.apache.org/books/dubbo-user-book/configuration/api.html
 */
public class ApiConsumer {
    public static void getPermission() {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-perm");

        String address = "zookeeper://127.0.0.1:2181"; // must same as provider

        // add registry addresses
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(address);

        ReferenceConfig<PermissionService> reference = new ReferenceConfig<PermissionService>();

        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(PermissionService.class);
        reference.setConnections(10);
        reference.setTimeout(1000);
        /*
         * FIXME, If the provider don't set version, also no need set, else can't match. if have multi-versions, can set * for all.
         */
        // reference.setVersion("*");

        PermissionService service = reference.get();

        List<String> permissions = service.getPermissions(2L);
        permissions.forEach(System.out::println);
    }

    public static void sayHello() {

        ApplicationConfig application = new ApplicationConfig();
        application.setName("api-hello");

        String address = "127.0.0.1:2181";

        // add registry addresses
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(address);

        ReferenceConfig<HelloService> reference = new ReferenceConfig<HelloService>();
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(HelloService.class);
        reference.setConnections(10);
        reference.setTimeout(1000);
        reference.setVersion("*"); // no special version
        reference.setId(UUID.randomUUID().toString());

        HelloService service = reference.get();

        String result = service.sayHello("world");
        System.out.println("Result: " + result);

    }

    public static void main(String[] args) {
        getPermission();
        sayHello();
    }

}
