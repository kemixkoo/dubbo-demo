package xyz.kemix.dubbo.demo.service;

import java.util.List;
import java.util.Map;

import xyz.kemix.dubbo.demo.model.Person;

/**
 * @author Kemix Koo
 *
 */
public interface HelloService {
    String sayHello(String name);

    List<String> sayWords(List<Person> persons);

    List<String> saySomething(Map<String, Person> persons);
}
