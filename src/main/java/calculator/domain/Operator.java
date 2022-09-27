package calculator.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.IntBinaryOperator;

import static java.util.stream.Collectors.toMap;

/**
 * Created by seungwoo.song on 2022-09-27
 */
public enum Operator {
    PLUS("+", (result, num) -> result + num),
    MINUS("-", (result, num) -> result - num),
    MULTIPLE("*", (result, num) -> result * num),
    DIVIDE("/", (result, num) -> result / num);

    private String code;
    private IntBinaryOperator operate;

    private static final Map<String, Operator> operatorMap;

    static {
        operatorMap = Arrays.stream(values())
                .collect(toMap(value -> value.code, value -> value));
    }

    Operator(String code, IntBinaryOperator operator) {
        this.code = code;
        this.operate = operator;
    }

    public int operate(Integer result, Integer number) {
        return operate.applyAsInt(result, number);
    }

    public static Operator parse(String code) {
        Operator operator = operatorMap.get(code);
        if (operator == null) {
            throw  new IllegalArgumentException("잘못된 연산자 입니다");
        }
        return operator;
    }

    public static boolean isOperator(String code) {
        return operatorMap.get(code) != null;
    }
}