package xyz.kemix.dubbo.demo.generic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * @author Kemix Koo
 *
 */
public class PojoGenericConsumer extends AbsConsumer {

    public void filter() {
        String interfaceName = "xyz.kemix.dubbo.demo.service.PersonService";
        String methodName = "filter";
        String clazzName = "xyz.kemix.dubbo.demo.model.Person";
        //
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setGeneric(true);

        reference.setApplication(createApp("pojo-filter"));
        reference.setRegistry(createRegistry());
        reference.setInterface(interfaceName);

        GenericService genericService = reference.get();

        Map<String, Object> p1 = new HashMap<>();
        p1.put("class", clazzName);
        p1.put("id", 1);
        p1.put("name", "Zhang");
        p1.put("age", 14);

        Map<String, Object> p2 = new HashMap<>();
        p2.put("class", clazzName);
        p2.put("id", 2);
        p2.put("name", "Wang");
        p2.put("age", 28);

        Map<String, Object> c1 = new HashMap<>();
        c1.put("class", clazzName);
        c1.put("id", 21);
        c1.put("name", "Wang-1");
        c1.put("age", 10);

        Map<String, Object> c2 = new HashMap<>();
        c2.put("class", clazzName);
        c2.put("id", 22);
        c2.put("name", "Wang-2");
        c2.put("age", 5);

        p2.put("children", Arrays.asList(c1, c2));

        Map<String, Object> additions = new HashMap<>();
        additions.put("post", 1234);
        additions.put("flag", true);
        p2.put("additions", additions);

        Object result = genericService.$invoke(methodName, new String[] { List.class.getName() }, new Object[] { Arrays.asList(p1, p2) });

        System.out.println(methodName + "返回结果: " + result);
        printServer();
    }

    public void convert() {
        String interfaceName = "xyz.kemix.dubbo.demo.service.PersonService";
        String methodName = "convert";
        String clazzName = "xyz.kemix.dubbo.demo.model.Person";
        //
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setGeneric(true);

        reference.setApplication(createApp("pojo-convert"));
        reference.setRegistry(createRegistry());
        reference.setInterface(interfaceName);

        GenericService genericService = reference.get();

        Map<String, Object> p1 = new HashMap<>();
        p1.put("class", clazzName);
        p1.put("id", 1);
        p1.put("name", "Zhang");
        p1.put("age", 14);

        Map<String, Object> p2 = new HashMap<>();
        p2.put("class", clazzName);
        p2.put("id", 2);
        p2.put("name", "Wang");
        p2.put("age", 28);

        Map<String, Object> c1 = new HashMap<>();
        c1.put("class", clazzName);
        c1.put("id", 21);
        c1.put("name", "Wang-1");
        c1.put("age", 10);

        Map<String, Object> c2 = new HashMap<>();
        c2.put("class", clazzName);
        c2.put("id", 22);
        c2.put("name", "Wang-2");
        c2.put("age", 5);

        p2.put("children", Arrays.asList(c1, c2));

        Map<String, Object> additions = new HashMap<>();
        additions.put("post", 1234);
        additions.put("flag", true);
        p2.put("additions", additions);

        Map<String, Object> values = new HashMap<>();
        values.put("Zhang", p1);
        values.put("Wang", p2);

        Object result = genericService.$invoke(methodName, new String[] { Map.class.getName() }, new Object[] { values });

        System.out.println(methodName + "返回结果: " + result);
        printServer();
    }

    public static void main(String[] args) {
        PojoGenericConsumer consumer = new PojoGenericConsumer();
        consumer.filter();
        consumer.convert();
    }

}
