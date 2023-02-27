package geeks4geeks;

import java.util.Scanner;
import java.util.Stack;

public class Tesst2 {
    public static boolean isBalanced(String s) {
        Stack<Character> stack1 = new Stack<Character>();
        Stack<Integer> stack2 = new Stack<Integer>();
        int result =0;
        int inner_result =1;
        for (int i=0; i<s.length();++i){
            if (s.charAt(i) == '('){

                if(stack1.isEmpty()) {
                    stack2.push(1);
                    stack1.push('(');
                }
                else {
                    stack2.push(2);
                    stack1.push('(');
                }
            }
            else
            {
                int r = stack1.pop();
                if(stack1.isEmpty()){
                    for (int ss=0; ss<stack2.size();ss++){
                        inner_result = inner_result * stack2.pop();
                    }
                    result =result+inner_result;
                    System.out.println("inner_result"+inner_result);
                    System.out.println("result "+result);
                    inner_result=1;
                    stack2.clear();
                }
            }

        }


        return stack1.isEmpty();
    }

    public static void main(String[] args) {
        Stack<Character> stack = new Stack<Character>();
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()){
            line = sc.nextLine();
            if (isBalanced(line)) System.out.println("true");
            else System.out.println("false");
        }
    }
}
