package com.example.reactor_demo.functional;

import java.util.ArrayList;
import java.util.List;

public class FunctionalExample {

    public static void main(String[] args) {
        List<String> namesList = List.of("alex", "ben", "chloe", "adam");
        List<String> newNamesList = namesGreaterThanSize(namesList, 3);
        System.out.println("newNamesList = " + newNamesList);
    }

    private static List<String> namesGreaterThanSize(List<String> namesList, int size) {
        return namesList.stream().filter(s -> s.length() > size).toList();
    }

}
