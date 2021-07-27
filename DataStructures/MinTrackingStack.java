package DataStructures;

import java.util.Stack;

public class MinTrackingStack {
    private Stack<Integer> data;
    private Stack<Integer> mins;

    public void push(int item){
        if(item <= mins.peek()){
            mins.push(item);
        }
        data.push(item);
    }

    public void pop(){
        if(data.peek() == mins.peek()){
            mins.pop();
        }
        data.pop();
    }

    public Integer min(){
        return mins.peek();
    }
}
