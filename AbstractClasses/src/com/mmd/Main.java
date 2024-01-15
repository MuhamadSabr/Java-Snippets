package com.mmd;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) { // U don't want an abstract class to be used for specific uses.
        //Animal animal = new Animal("Wolf", "Big", 200);  //Animal is abstract cannot be instantiated. Though u can {implement it at this statement}
        Animal dog = new Dog("Wolf", Size.LARGE, 250);  // U can create an instance of a concrete class n have a abstract variable reference to it
        doAnimalStuff(dog, "Fast");

        Fish fish = new Fish("Gold fish", Size.EXTRA_SMALL, 2);
        doAnimalStuff(fish, "fast");

        ArrayList<Animal> animalArrayList = new ArrayList<>();
        animalArrayList.add(new Dog("Pug", Size.SMALL, 15));
        animalArrayList.add(new Fish("Erl fish", Size.EXTRA_SMALL, 1));
        animalArrayList.add(new Fish("Octupus", Size.SMALL, 5));
        animalArrayList.add(new Dog("German shepered", Size.SMALL, 10));
        animalArrayList.add(new Horse("Black horse", Size.LARGE, 250));
        for(Animal animal : animalArrayList){
            doAnimalStuff(animal, "fast");
            if(animal instanceof Horse horse){  //Pattern matching version of the instanceof operator
                //( (Horse)  animal).shedsHair();
                horse.shedsHair();
            }
        }

    }

    private static void doAnimalStuff(Animal animal, String speed){ //If u make it non-static it cannot be used on main since main method is static,
        animal.move(speed);                                         // n main has to be static otherwise it is not recognized n the program cannot be run.
        animal.makeNoise();                                         // Even though the class is abstract yet it can be used to generalize in this way
    }
}