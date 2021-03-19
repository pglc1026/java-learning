package jl.algorithm.enums;

/**
 * TODO
 *
 * @author Liu Chang
 * @date 2021/3/8
 */
public class TestEnumContext {


    public enum TestEnum implements Test {


        TEST1 {
            @Override
            public String test() {
                throw new RuntimeException();
            }
        }

    }

    public static void main(String[] args) {
        for (TestEnum value : TestEnum.values()) {
            value.test();
        }
    }

}
