package com.nirvana.habits.journal.todo.reflection;

import com.nirvana.habits.journal.todo.Todo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


public class DomainObjectMapping {
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class c = Class.forName("com.nirvana.habits.journal.todo.Todo");
//        Constructor array
        Constructor[] constructors = c.getDeclaredConstructors();
        Todo entity = (Todo) c.newInstance();
        entity.setCompleted(true);
        entity.setId(1l);
        c.isSealed();
        System.out.println("value ===="+ entity);
        Arrays.stream(constructors).map(con->Arrays.stream(con.getParameters()).collect(Collectors.toList())).forEach(System.out::println);
         Method[] methods = c.getDeclaredMethods();
         Arrays.stream(methods).sequential().forEachOrdered(System.out::println);
        Class[] classes = c.getDeclaredClasses();
        System.out.println("=========");
        Arrays.stream(classes).forEach(System.out::println);
//        Annotations
        Annotation[] anno = c.getDeclaredAnnotations();
        for(Annotation annotation : anno) {
            System.out.println("Annotation: "+annotation);
        }
         
    }
 
}