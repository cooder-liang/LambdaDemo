public class Main {

    /**
     * // 1. 不需要参数,返回值为 5
     * x -> 5
     * // 2. 接收一个参数(数字类型),返回其2倍的值
     * x -> 2 * x;
     * <p>
     * // 3. 接受2个参数(数字),并返回他们的差值
     * (x,y)->x-y;
     * // 4. 接收2个int型整数,返回他们的和
     * (int x,int y)->x+y;
     * // 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
     * (String s) ->System.out.print(s);
     *
     * @param args
     */

    public static void main(String[] args) {

        //类型申明
        MathOperation addition = (int a, int b) -> a + b;

        //不用类型申明
        MathOperation subtraction = (a, b) -> a - b;

        //大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        //没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        //不用括号
        GreetService greetService =
                message -> System.out.println("Hello:" + message);

        //用括号
        GreetService greetService1 =
                (message) -> System.out.println("HelloWord"+message);

        System.out.println(""+operate(1,2,addition));
        System.out.println(""+operate(1,2,subtraction));
        System.out.println(""+operate(1,2,multiplication));
        System.out.println(""+operate(1,2,division));

        greetService.sayMessage("Nick");
        greetService1.sayMessage("Mark");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetService {
        void sayMessage(String msg);
    }

    public static int operate(int a, int b, MathOperation operation) {
        return operation.operation(a, b);
    }
}
