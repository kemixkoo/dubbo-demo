package xyz.kemix.dubbo.demo.generic;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * Without any dependency of service api, pure dubbo apis.
 * 
 * @author Kemix Koo
 *
 *         more details: http://dubbo.incubator.apache.org/books/dubbo-user-book/demos/generic-reference.html
 */
public class PureGenericConsumer extends AbsGenericConsumer {
    public void sayObjectHello() {
        String interfaceName = "xyz.kemix.dubbo.demo.service.HelloService";

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
        // can't use generic Object for prarameter types, will throw NoSuchMethodException
        Object result = genericService.$invoke("sayHello", new String[] { Object.class.getName() }, new Object[] { "world" });

        System.out.println("返回结果: " + result);
        printServer();
    }

    public static void main(String[] args) {
        System.out.println("开始调用远程服务...");

        PureGenericConsumer consumer = new PureGenericConsumer();
        consumer.getPermission();
        consumer.sayHello();

        // consumer.sayObjectHello();
    }

}
