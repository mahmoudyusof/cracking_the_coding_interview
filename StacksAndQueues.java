import java.util.Stack;

public class StacksAndQueues {
    public static Stack<Integer> sortStack(Stack<Integer> s) {
        Stack<Integer> sorted = new Stack<>();
        while(!s.isEmpty()){
            if(sorted.isEmpty() || s.peek() < sorted.peek()){
                sorted.push(s.pop());
            }else{
                int temp = s.pop();
                int count = 0;
                while(!sorted.isEmpty() && temp > sorted.peek()){
                    s.push(sorted.pop());
                    count++;
                }
                sorted.push(temp);
                for(int i=0; i<count; i++){
                    sorted.push(s.pop());
                }
            }
        }
        return sorted;
    }

    public static void testSortStack(){
        Stack<Integer> s = new Stack<>();
        s.push(5);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(0);
        s.push(1);

        Stack<Integer> sorted = sortStack(s);

        assert !sorted.isEmpty();
        int count = 0;
        while(!sorted.isEmpty()){
            int value = sorted.pop();
            assert value == count;
            count++;
        }
    }

    public static void main(String[] args) {
        testSortStack();
    }
}
