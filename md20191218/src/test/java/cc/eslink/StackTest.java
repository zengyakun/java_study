package cc.eslink;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *@ClassName StackTest
 *@Description
 *@Author zeng.yakun (0178)
 *@Date 2020/1/12 14:10
 *@Version 1.0
 **/
public class StackTest {

    public static void main(String[] args) {
        String str = "1+((2+3)*4)-5+6*9/3+2-1";
        String ts = StackTest.transfer(str);
        int rt = StackTest.calculate(ts);
        System.out.println("中缀表达式：" + str + "，后缀表达式（逆波兰表达式）：" + ts + "，表达式结果：" + rt);
    }

    /**
     * @Description 中缀表达式转后缀表达式测试
     * @Author zeng.yakun (0178)
     * @Date 2020/1/12 14:18
     * @param
     * @return
     **/
    @Test
    public void test() {
        String str = "1+((2+3)*4)-5";
        System.out.println(StackTest.transfer(str));
    }

    /**
     * @Description 中缀表达式转后缀表达式，比如：1+((2+3)*4)-5
     * 1+((2+3)*4)-5+6*9/3+2-1
     * 转换规则
     * 1）我们使用一个stack栈结构存储操作符，用一个List结构存储后缀表达式结果
     * 2）首先读取到数字，直接存入list中
     * 3）当读取到左括号"("时，直接压栈
     * 4) 当遇到右括号")"时，循环执行出栈操作并加入到list中，直到遇到左括号为止。并将左括号弹出，但不加入list中
     * 5）当读取到运算符时，分两种情况讨论
     *  a.当运算符栈为空或者左括号（或者栈顶操作符的优先级小于当前运算符优先级时(如+和-的优先级低于 * 和 /)，直接入栈
     *  b.当运算符不为空时且栈顶操作符的优先级大于或等于当前运算符优先级时，循环执行出栈操作并加入list中，
     *  直到遇到优先级小于当前运算符的元素为止。循环执行完后再将当前运算符压栈。另外需要注意的是，只有遇到右括号)时，左括号(才出栈
     * 6) 表达式的值读取完后，将操作符栈中的所有元素弹出并加入到list中
     * 执行完上面步骤后，list中存储的顺序即为我们转换后的后缀表达式的结果
     * @Author zeng.yakun (0178)
     * @Date 2020/1/12 14:22
     * @param str
     * @return
     **/
    private static String transfer(String str) {
        List<String> expressionList = new ArrayList<>(str.length());
        for (char c : str.toCharArray()) {
            expressionList.add(String.valueOf(c));
        }
        //创建一个栈用于保存操作符
        Stack<String> opStack = new Stack<>();
        //创建一个list用于保存后缀表达式
        List<String> suffixList = new ArrayList<>();
        for (String item : expressionList) {
            //得到数或操作符
            if (isOperator(item)) {
                //是操作符 判断操作符栈是否为空
                if (opStack.isEmpty() || "(".equals(opStack.peek()) || priority(item) > priority(opStack.peek())) {
                    //为空或者栈顶元素为左括号或者当前操作符大于栈顶操作符直接压栈
                    opStack.push(item);
                } else {
                    //当运算符不为空时且栈顶操作符的优先级大于或等于当前运算符优先级时，循环执行出栈操作并加入list中，
                    // 直到遇到优先级小于当前运算符或者左括号（的元素为止。循环执行完后再将当前运算符压栈
                    while (!opStack.isEmpty() && !"(".equals(opStack.peek())) {
                        if (priority(item) <= priority(opStack.peek())) {
                            suffixList.add(opStack.pop());
                        } else {
                            break;
                        }
                    }
                    //当前操作符压栈
                    opStack.push(item);
                }
            } else if (isNumber(item)) {
                //是数字则直接入队
                suffixList.add(item);
            } else if ("(".equals(item)) {
                //是左括号，压栈
                opStack.push(item);
            } else if (")".equals(item)) {
                //是右括号 ，将栈中元素弹出入队，直到遇到左括号，左括号出栈，但不入队
                while (!opStack.isEmpty()) {
                    if ("(".equals(opStack.peek())) {
                        opStack.pop();
                        break;
                    } else {
                        suffixList.add(opStack.pop());
                    }
                }
            } else {
                throw new RuntimeException("有非法字符！");
            }
        }
        //循环完毕，如果操作符栈中元素不为空，将栈中元素出栈入队
        while (!opStack.isEmpty()) {
            suffixList.add(opStack.pop());
        }
        return String.join("", suffixList);
    }

    /**
     * 判断字符串是否为操作符
     * @param op
     * @return
     */
    public static boolean isOperator(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }

    /**
     * 判断是否为数字
     * @param num
     * @return
     */
    public static boolean isNumber(String num) {
        return num.matches("\\d+");
    }

    /**
     * 获取操作符的优先级
     * @param op
     * @return
     */
    public static int priority(String op) {
        if (op.equals("*") || op.equals("/")) {
            return 1;
        } else if (op.equals("+") || op.equals("-")) {
            return 0;
        }
        return -1;
    }

    /**
     * @Description 后缀表达式求值测试
     * @Author zeng.yakun (0178)
     * @Date 2020/1/12 14:18
     * @param
     * @return
     **/
    @Test
    public void test2() {
        String str = "123+4*+5-";
//        str = "123+4*+56*32+/+-1-";
        System.out.println(StackTest.calculate(str));
    }

    /**
     * 根据后缀表达式list计算结果，比如：123+4*+5-
     * 计算逻辑：
     * 1.遍历表达式，遇到数字时直接入栈
     * 2.遇到操作符，则将栈顶和次栈顶元素出栈与操作符进行运算，然后将结果压入栈
     * @param str
     * @return
     */
    private static int calculate(String str) {
        List<String> list = new ArrayList<>(str.length());
        for (char c : str.toCharArray()) {
            list.add(String.valueOf(c));
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            if (item.matches("\\d+")) {
                //是数字
                stack.push(Integer.parseInt(item));
            } else {
                //是操作符，取出栈顶两个元素
                int num2 = stack.pop();
                int num1 = stack.pop();
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符错误！");
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }
}
