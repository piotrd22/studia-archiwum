package org.example.stack;

public class Stack {
    private int[] stack;
    private int top;
    private int capacity;

    public Stack() {
        this.capacity = 10;
        this.stack = new int[this.capacity];
        this.top = -1;
    }

    public int pop() {
        if(this.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.top -= 1;
        return this.stack[this.top + 1];
    }

    public void push(int item) {
        if (isFull()) {
            int[] newArr = new int[this.capacity + 10];
            System.arraycopy(this.stack, 0, newArr, 0, this.capacity);
            this.stack = newArr;
            this.capacity += 10;
        }
        this.stack[this.top + 1] = item;
        this.top += 1;
    }

    public int getSize() {
        return this.top + 1;
    }
    public void printStack() {
        System.out.print("Stack: ");
        for (int i = 0; i <= this.top; i++) {
            System.out.print(this.stack[i] + " ");
        }
        System.out.println();
    }
    private boolean isFull() {
        return this.top == this.capacity - 1;
    }
    private boolean isEmpty() {
        return this.top == -1;
    }
}
