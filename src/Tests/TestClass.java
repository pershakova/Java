package Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TestClass {
    @Test
    public void array1RuntimeException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            int[] arr = {0, 1, 2, 3};
            TestsMain.arrayAfterLastFour(arr);
        });
    }

        static Stream<Arguments> generateData() {
            return Stream.of(
                    Arguments.of(new int[]{4, 1, 2, 3}, new int[]{1, 2, 3}),
                    Arguments.of(new int[]{3, 4, 4, 3, 4, 7, 6}, new int[]{7, 6}),
                    Arguments.of(new int[]{4, 1, 4, 2}, new int[]{2})
            );
        }

        @ParameterizedTest
        @MethodSource("generateData")
        public void array1Input(int[] input, int[] result){
            Assertions.assertArrayEquals(result, TestsMain.arrayAfterLastFour(input));
        }

    static Stream<Arguments> generateData2() {
        return Stream.of(
                Arguments.of(new int[]{4, 1, 1, 3}, false),
                Arguments.of(new int[]{1, 1, 1, 1}, false),
                Arguments.of(new int[]{4, 4, 4, 4}, false),
                Arguments.of(new int[]{1, 1, 4, 1, 4}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("generateData2")
    public void array2Input(int[] input, boolean result){
        Assertions.assertEquals(result, TestsMain.arrayFromFourAndOne(input));
    }
}
