package DataStructures.Stacks;

import java.util.EmptyStackException;

public class MultiStack {
    private int[] array;
    private int stack_size;
    private int num_stacks = 3;
    private int[] stack_pointers = new int[] { 0, 0, 0 };

    public MultiStack(int size) {
        this.stack_size = size;
        this.array = new int[this.stack_size * this.num_stacks];
    }

    /**
     * pushes to stack at index stack_index
     * @param item
     * @param stack_index
     * @throws StackOverflowError
     */
    public void push(int item, int stack_index) throws StackOverflowError {
        if (this.stack_pointers[stack_index] >= (stack_index + 1) * stack_size) {
            throw new StackOverflowError();
        }

        this.array[stack_index * stack_size + stack_pointers[stack_index]] = item;
        stack_pointers[stack_index]++;
    }

    /**
     * pops form stack at stack_index
     * @param stack_index
     * @return
     * @throws EmptyStackException
     */
    public int pop(int stack_index) throws EmptyStackException {
        if (stack_pointers[stack_index] == 0) {
            throw new EmptyStackException();
        }

        int value = this.array[stack_index * stack_size + stack_pointers[stack_index]];
        stack_pointers[stack_index]--;
        return value;
    }
}
