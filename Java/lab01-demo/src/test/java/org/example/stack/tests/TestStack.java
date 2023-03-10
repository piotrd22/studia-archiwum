package org.example.stack.tests;

import org.example.stack.Stack;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestStack {
    private Stack stack;

    @Before
    public void setup() {
        this.stack = new Stack();
    }

    @Test
    public void push() {
        for (int i = 0; i < 11; i++) {
            this.stack.push(i);
        }
        int result = this.stack.getSize();

        assertEquals("getSize", 11, result);
    }

    @Test
    public void getSize() {
        for (int i = 0; i < 11; i++) {
            this.stack.push(i);
        }
        int result = this.stack.getSize();

        assertEquals("getSize", 11, result);
    }

    @Test
    public void popFailure() {
        assertThrows(IllegalArgumentException.class, () -> this.stack.pop());
    }

    @Test
    public void pop() {
        for (int i = 0; i < 120; i++) {
            this.stack.push(i);
        }
        int result = this.stack.pop();
        int size = this.stack.getSize();

        assertEquals("pop", 119, result);
        assertEquals("pop", 119, size);
    }

    @Test
    public void peek() {
        for (int i = 0; i < 120; i++) {
            this.stack.push(i);
        }
        int result = this.stack.peek();
        int size = this.stack.getSize();

        assertEquals("pop", 119, result);
        assertEquals("pop", 120, size);
    }

    @Test
    public void peekFailure() {
        assertThrows(IllegalArgumentException.class, () -> this.stack.peek());
    }
}
