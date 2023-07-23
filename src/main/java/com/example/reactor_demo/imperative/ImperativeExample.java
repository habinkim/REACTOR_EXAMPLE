package com.example.reactor_demo.imperative;

import java.util.ArrayList;
import java.util.List;

public class ImperativeExample {

    public static void main(String[] args) {
        List<String> namesList = List.of("alex", "ben", "chloe", "adam");
        List<String> newNamesList = namesGreaterThanSize(namesList, 3);
        System.out.println("newNamesList = " + newNamesList);
    }

    private static List<String> namesGreaterThanSize(List<String> namesList, int size) {
        List<String> newNamesList = new ArrayList<>();
        for (String name : namesList) {
            if (name.length() > size) {
                newNamesList.add(name);
            }
        }
        return newNamesList;
    }

}
