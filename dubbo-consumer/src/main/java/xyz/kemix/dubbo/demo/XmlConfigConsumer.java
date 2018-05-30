package xyz.kemix.dubbo.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.kemix.dubbo.demo.service.HelloService;
import xyz.kemix.dubbo.demo.service.PermissionService;

/**
 * @author Kemix Koo
 *
 */
public class XmlConfigConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("开始调用远程服务...");

        PermissionService permissionService = context.getBean(PermissionService.class);
        System.out.println("服务返回： :" + permissionService.getPermissions(1L));

        HelloService helloService = context.getBean(HelloService.class);
        System.out.println("服务返回： :" + helloService.sayHello("World"));
        context.close();
    }
}
