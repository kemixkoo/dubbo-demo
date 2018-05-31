package xyz.kemix.dubbo.demo.service;

import java.util.List;
import java.util.Map;

import xyz.kemix.dubbo.demo.model.Person;

/**
 * @author Kemix Koo
 *
 */
public interface PersonService {
    boolean valid(Person person);

    List<Person> filter(List<Person> persons);

    Map<Integer, Person> convert(Map<String, Person> persons);
}
