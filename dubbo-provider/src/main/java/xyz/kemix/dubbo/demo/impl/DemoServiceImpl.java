package xyz.kemix.dubbo.demo.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import xyz.kemix.dubbo.demo.model.Person;
import xyz.kemix.dubbo.demo.service.HelloService;
import xyz.kemix.dubbo.demo.service.PermissionService;
import xyz.kemix.dubbo.demo.service.PersonService;

/**
 * @author Kemix Koo
 *
 */
public class DemoServiceImpl implements HelloService, PermissionService, PersonService {

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

    @Override
    public boolean valid(Person person) {
        return person != null && person.getAge() > 18;
    }

    @Override
    public List<Person> filter(List<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Collections.emptyList();
        }
        return persons.stream().filter(p -> valid(p)).collect(Collectors.toList());
    }

    @Override
    public Map<Integer, Person> convert(Map<String, Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return Collections.emptyMap();
        }
        return  persons.values().stream().collect(Collectors.toMap(Person::getId, Function.identity()));
    }

}
