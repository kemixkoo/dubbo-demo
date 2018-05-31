package xyz.kemix.dubbo.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.dubbo.config.ReferenceConfig;

import xyz.kemix.dubbo.demo.generic.AbsConsumer;
import xyz.kemix.dubbo.demo.model.Person;
import xyz.kemix.dubbo.demo.service.HelloService;
import xyz.kemix.dubbo.demo.service.PermissionService;
import xyz.kemix.dubbo.demo.service.PersonService;

/**
 * @author Kemix Koo
 *
 *         more details: http://dubbo.incubator.apache.org/books/dubbo-user-book/configuration/api.html
 */
public class ApiConsumer extends AbsConsumer {
    public void getPermissions() {
        ReferenceConfig<PermissionService> reference = new ReferenceConfig<PermissionService>();
        reference.setApplication(createApp("api-perm"));
        reference.setRegistry(createRegistry());
        reference.setInterface(PermissionService.class);
        reference.setConnections(10);
        reference.setTimeout(1000);

        /*
         * FIXME, If the provider don't set version, also no need set, else can't match. if have multi-versions, can set * for all.
         */
        // reference.setVersion("*");

        PermissionService service = reference.get();

        List<String> permissions = service.getPermissions(2L);
        System.out.println("返回结果:");
        permissions.forEach(System.out::println);
        printServer();
    }

    /**
     * if don't set the HelloService in provider, because can't find it, will have timeout error
     */
    public void sayHello() {

        ReferenceConfig<HelloService> reference = new ReferenceConfig<HelloService>();
        reference.setApplication(createApp("api-hello"));
        reference.setRegistry(createRegistry());
        reference.setInterface(HelloService.class);
        reference.setConnections(10);
        reference.setTimeout(1000);
        /*
         * FIXME, If the provider don't set version, also no need set, else can't match. if have multi-versions, can set * for all.
         */
        // reference.setVersion("*");

        HelloService service = reference.get();

        String result = service.sayHello("world");
        System.out.println("返回结果: " + result);
        printServer();
    }

    public void filter() {
        ReferenceConfig<PersonService> reference = new ReferenceConfig<PersonService>();
        reference.setApplication(createApp("api-filter"));
        reference.setRegistry(createRegistry());
        reference.setInterface(PersonService.class);
        reference.setConnections(10);
        reference.setTimeout(1000);

        PersonService service = reference.get();

        List<Person> filtered = service.filter(Arrays.asList(new Person(1, "a", 13), new Person(2, "b", 20),new Person(3, "c", 25)));
        System.out.println("返回结果: " + filtered.stream().map(p -> p.getName() + " " + p.getAge()).collect(Collectors.joining(",")));
        printServer();
    }

    public static void main(String[] args) {
        System.out.println("开始调用远程服务...");

        ApiConsumer consumer = new ApiConsumer();
        consumer.getPermissions();
        consumer.sayHello();
        consumer.filter();
    }

}
