import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                (message) -> System.out.println("HelloWord" + message);

        System.out.println("" + operate(1, 2, addition));
        System.out.println("" + operate(1, 2, subtraction));
        System.out.println("" + operate(1, 2, multiplication));
        System.out.println("" + operate(1, 2, division));

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


    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("thread run...");
        }
    });

    Thread threadLambda = new Thread(() -> {
        System.out.println("hello lambda...");
    });


    public void method1() {
        List<String> list = Arrays.asList(new String[]{"1", "2", "3"});
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Collections.sort(list, (str1, str2) -> str1.compareTo(str2));
    }

    public void method2() {
        List<String> list = Arrays.asList(new String[]{"Ni", "Hao", "Mark"});
        List<String> lowcaseList = new ArrayList<>();
        for (String lowcase : list) {
            lowcaseList.add(lowcase);
        }

        List<String> lambdaStreamList = list.stream()
                .map(name -> name.toLowerCase())
                .collect(Collectors.toList());

        list.stream().map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    @Test
    public void method3() {
        String str1 = "Lambda：";
        List<String> msgs = Arrays.asList(new String[]{"Ni", "Hao", "Mark"});
        List<String> lmsgs = msgs.stream()
                .map(names -> {
                    return str1 + names + "-------" + System.currentTimeMillis();
                }).collect(Collectors.toList());
        lmsgs.forEach(System.out::println);
    }

    @Test
    public void method4() {
        List<String> nums = Arrays.asList(new String[]{"1", null, "2", "6", null, null});

        List<String> collect = nums.stream()
                .filter(num -> num != null)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

    }

    @Test
    public void method5() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Stream<String> taobao = Stream.of("taobao");

        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });

        Stream.generate(() -> Math.random())
                .limit(2)
                .forEach(System.out::println);
        System.out.println("--------------------");
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    @Test
    public void method6() {
        List<String> nums = Arrays.asList
                (new String[]{"1", null, "2", "6", null, null});

        nums.stream()
                .distinct()
                .filter(s -> s != null && !s.equals("2"))
                .forEach(System.out::println);
    }

    @Test
    public void method7() {
        List<String> nums = Arrays.asList
                (new String[]{"1", "3", "2", "6", "7", "0"});

        int sum = nums.stream().mapToInt(num -> Integer.parseInt(num)).sum();
        System.out.println(sum);

    }

    @Test
    public void method8() {
        Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        ).flatMap(num -> num.stream())
                .forEach(System.out::println);

    }

    @Test
    public void method9(){
        List<String> nums =
                Arrays.asList(new String[]{"1",null,"1","2","3","4",null
                ,"5","6","7","8","9","10"});

        int sum = nums.stream()
                .filter(num -> num != null)
                .distinct()
                .mapToInt(num1 -> Integer.parseInt(num1))
                .map(num2 -> 2 * num2)
                .peek(System.out::println)
                .skip(2)
                .limit(4)
                .sum();

        System.out.println(sum);

    }

}
