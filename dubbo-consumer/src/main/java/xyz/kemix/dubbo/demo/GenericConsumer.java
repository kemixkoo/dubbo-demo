package xyz.kemix.dubbo.demo;

import xyz.kemix.dubbo.demo.generic.AbsGenericConsumer;

/**
 * @author Kemix Koo
 *
 *         more details: http://dubbo.incubator.apache.org/books/dubbo-user-book/demos/generic-reference.html
 */
public class GenericConsumer extends AbsGenericConsumer {

    public static void main(String[] args) {
        System.out.println("开始调用远程服务...");

        GenericConsumer consumer = new GenericConsumer();
        consumer.getPermission();
        consumer.sayHello();
    }

}
