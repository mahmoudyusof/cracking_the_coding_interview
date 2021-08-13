import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.Test;

import DataStructures.Stacks.*;

public class StacksAndQueues {
    /**
     * Sorts a stack using another stack
     * @param stack
     * @return sorted stack
     */
    public static Stack<Integer> sortStack(Stack<Integer> stack) {
        Stack<Integer> sorted = new Stack<>();
        while (!stack.isEmpty()) {
            if (sorted.isEmpty() || stack.peek() < sorted.peek()) {
                sorted.push(stack.pop());
            } else {
                int temp = stack.pop();
                int count = 0;
                while (!sorted.isEmpty() && temp > sorted.peek()) {
                    stack.push(sorted.pop());
                    count++;
                }
                sorted.push(temp);
                for (int i = 0; i < count; i++) {
                    sorted.push(stack.pop());
                }
            }
        }
        return sorted;
    }

    @Test
    public void testSortStack() {
        Stack<Integer> s = new Stack<>();
        s.push(5);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(0);
        s.push(1);

        Stack<Integer> sorted = sortStack(s);

        assertFalse(sorted.isEmpty());
        int count = 0;
        while (!sorted.isEmpty()) {
            int value = sorted.pop();
            assertEquals(count, value);
            assert value == count;
            count++;
        }
    }

    @Test
    public void testMinStack(){
        MyMinStack<Integer> minStack = new MyMinStack<>();
        for(int i=4; i>0; i--){
            minStack.push(i);
        }
        for(int i=0; i<4; i++){
            minStack.push(i);
        }

        assertEquals(Integer.valueOf(0), minStack.min());

        for(int i=0; i<4; i++){
            minStack.pop();
        }

        assertEquals(Integer.valueOf(1), minStack.min());
    }
}
