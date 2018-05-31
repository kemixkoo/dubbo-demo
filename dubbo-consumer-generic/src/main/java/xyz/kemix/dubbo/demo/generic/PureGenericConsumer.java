package xyz.kemix.dubbo.demo.generic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public void sayWords() {
        String interfaceName = "xyz.kemix.dubbo.demo.service.HelloService";
        String methodName = "sayWords";
        //
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setGeneric(true);

        reference.setApplication(createApp("gen-words"));
        reference.setRegistry(createRegistry());
        reference.setInterface(interfaceName);

        GenericService genericService = reference.get();

        Map<String, Object> p1 = new HashMap<>();
        p1.put("class", "xyz.kemix.dubbo.demo.model.Person");
        p1.put("id", 1);
        p1.put("name", "Gu");
        p1.put("age", 22);

        Map<String, Object> p2 = new HashMap<>();
        p2.put("class", "xyz.kemix.dubbo.demo.model.Person");
        p2.put("id", 2);
        p2.put("name", "Koo");
        p2.put("age", 30);

        Object result = genericService.$invoke(methodName, new String[] { List.class.getName() }, new Object[] { Arrays.asList(p1, p2) });

        System.out.println(methodName + "返回结果: " + result);
        printServer();
    }

    public void saySomething() {
        String interfaceName = "xyz.kemix.dubbo.demo.service.HelloService";
        String methodName = "saySomething";
        //
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setGeneric(true);

        reference.setApplication(createApp("gen-something"));
        reference.setRegistry(createRegistry());
        reference.setInterface(interfaceName);

        GenericService genericService = reference.get();

        Map<String, Object> p1 = new HashMap<>();
        p1.put("class", "xyz.kemix.dubbo.demo.model.Person");
        p1.put("id", 1);
        p1.put("name", "Gu");
        p1.put("age", 22);

        Map<String, Object> p2 = new HashMap<>();
        p2.put("class", "xyz.kemix.dubbo.demo.model.Person");
        p2.put("id", 2);
        p2.put("name", "Koo");
        p2.put("age", 30);

        Map<String, Object> value = new LinkedHashMap<>();
        value.put("Hello", p1);
        value.put("Hi", p2);
        Object result = genericService.$invoke(methodName, new String[] { Map.class.getName() }, new Object[] { value });

        System.out.println(methodName + "返回结果: " + result);
        printServer();
    }

    public static void main(String[] args) {
        System.out.println("开始调用远程服务...");

        PureGenericConsumer consumer = new PureGenericConsumer();
        consumer.getPermissions();
        consumer.sayHello();

        // consumer.sayObjectHello();

        consumer.sayWords();
        consumer.saySomething();
    }

}
