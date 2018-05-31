package xyz.kemix.dubbo.demo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import xyz.kemix.dubbo.demo.model.Person;
import xyz.kemix.dubbo.demo.service.HelloService;
import xyz.kemix.dubbo.demo.service.PermissionService;

/**
 * @author Kemix Koo
 *
 */
public class DemoServiceImpl implements HelloService, PermissionService {

    @Override
    public String sayHello(String name) {
        System.out.println("init : " + name);
        return "hello " + name;

    }

    @Override
    public List<String> sayWords(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.println("say words for " + persons.size() + " Persons");
        
        List<String> words = new ArrayList<>(persons.size());
        for (Person p : persons) {
            words.add("My name is " + p.getName() + ", I'm " + p.getAge() + " years old");
        }
        return words;
    }

    @Override
    public List<String> saySomething(Map<String, Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Collections.emptyList();
        }
        System.out.println("say something for " + persons.size() + " Persons");
        
        List<String> something = new ArrayList<>(persons.size());
        for (Entry<String, Person> entry : persons.entrySet()) {
            Person p = entry.getValue();
            something.add(entry.getKey() + ' ' + p.getName());
        }
        return something;
    }

    @Override
    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }

}
