package com.epam.third.run;

import java.util.ArrayDeque;

public class Main {

    private static ArrayDeque<Integer> deque = new ArrayDeque<>();

    public static void main(String... param) {
        deque.push(1);
        deque.push(2);
        deque.push(3);
        deque.push(4);
        deque.push(5);

        deque.pop();

        deque.forEach(item -> System.out.println(item.toString()));
    }
}
