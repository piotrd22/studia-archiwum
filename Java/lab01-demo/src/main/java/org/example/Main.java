package org.example;

import org.example.stack.Stack;

public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push(1);
        stack.push(10);
        stack.push(23);
        stack.push(34);
        stack.push(-3);
        stack.push(6);
        stack.push(7);
        stack.push(9);
        stack.push(12);
        stack.push(4);
        stack.push(12);

        stack.printStack();
        System.out.println(stack.getSize());

        stack.pop();

        stack.printStack();
        System.out.println(stack.getSize());
    }
}