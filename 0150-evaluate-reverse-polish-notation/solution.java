class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens){
            switch(token){
                case "+":{
                    stack.push(stack.pop() + stack.pop());
                    break;
                }
                case "-":{
                    int a = stack.pop();
                    stack.push(stack.pop() - a);
                    break;
                }
                case "*":{
                    stack.push(stack.pop()*stack.pop());
                    break;
                }
                case "/":{
                    int a = stack.pop();
                    stack.push(stack.pop()/a);
                    break;
                }
                default:{
                    stack.push(Integer.parseInt(token));
                }
            }
        }
        return stack.pop();
    }
}
