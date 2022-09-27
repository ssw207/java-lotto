package calculator;

import calculator.domain.CalculateResult;
import calculator.domain.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by seungwoo.song on 2022-09-27
 */
public class CalculatorTest {

    private final Calculator calculator = new Calculator(new CalculateResult());

    @Test
    void 더하기() {
        assertThat(calculator.calculate("3 + 2")).isEqualTo(5);
    }

    @Test
    void 빼기() {
        assertThat(calculator.calculate("3 - 2")).isEqualTo(1);
    }

    @Test
    void 곱하기() {
        assertThat(calculator.calculate("3 * 2")).isEqualTo(6);
    }

    @Test
    void 나누기() {
        assertThat(calculator.calculate("3 / 2")).isEqualTo(1);
    }

    @Test
    void 연산() {
        assertThat(calculator.calculate("2 + 3 * 4 / 2")).isEqualTo(10);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  "})
    void 공백입력(String input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> calculator.calculate(input))
                .withMessageMatching("입력값이 없습니다.");
    }
}