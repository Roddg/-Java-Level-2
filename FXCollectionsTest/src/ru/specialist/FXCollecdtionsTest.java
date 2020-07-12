package ru.specialist;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.*;
import static java.lang.System.out;

class Person
{
    public String name;
    public int age;
    
    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", name, age);
    }
    
    
}

public class FXCollecdtionsTest {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();
        
        persons.add(new Person("Sergey", 40));
        //persons.add(new Person("Kosty", 11));
        
        //persons.remove(0);
        
        
        ObservableList<Person> p2 = 
                FXCollections.observableList(persons);
        p2.addListener((Observable o) -> {
            out.println("invalidated");
        });
        
        
        //p2.add(new Person("Sergey", 40));
        p2.add(new Person("Kosty", 11));        
        
        
        
        
        p2.addListener((ListChangeListener.Change<? extends Person> c) -> {
            out.println(c.getList());
        });
        
        p2.add(new Person("Alex", 8));
        
        
        p2.get(0).age++;
        
        
       
        
        //persons.add(new Person("Alex", 5));
        
        //out.print("Persons : ");
        persons.stream().forEach(out::print);
        
       /* 
       // out.print("\nObservable Persons : ");
        p2.stream().forEach(out::print);
        */
    }
    
}
