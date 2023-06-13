package jl.pattern.chain;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * FilterChainTest
 *
 * @author Liu Chang
 * @date 2020/6/6
 */
public class FilterChainTest {

    private static List<Filter> filters = Lists.newArrayList(new Filter1(), new Filter2(), new Filter3());

    public static void main(String[] args) {
        Invoker last = new PrintInvoker();
        for (Filter filter : filters) {
            final Invoker next = last;
            last = new Invoker() {
                @Override
                public Result invoke(Invocation invocation) {
                    return filter.invoke(next, invocation);
                }
            };
        }

        Result result = last.invoke(new NumberInvocation(1));
        System.out.println(result);
    }

    @AllArgsConstructor
    static class NumberInvocation implements Invocation {

        private Integer number;

        @Override
        public Integer getNumber() {
            return this.number;
        }

        @Override
        public void setNumber(Integer number) {
            this.number = number;
        }

        @Override
        public void printNumber() {
            System.out.println(this.number);
        }
    }

    static class Filter1 implements Filter {

        @Override
        public Result invoke(Invoker invoker, Invocation invocation) {
            invocation.setNumber(invocation.getNumber() + 1);
            System.out.println(this.getClass().getSimpleName() + "invoke.");
            return invoker.invoke(invocation);
        }

    }

    static class Filter2 implements Filter {

        @Override
        public Result invoke(Invoker invoker, Invocation invocation) {
            invocation.setNumber(invocation.getNumber() + 2);
            System.out.println(this.getClass().getSimpleName() + "invoke.");
            return invoker.invoke(invocation);
        }

    }

    static class Filter3 implements Filter {

        @Override
        public Result invoke(Invoker invoker, Invocation invocation) {
            invocation.setNumber(invocation.getNumber() + 3);
            System.out.println(this.getClass().getSimpleName() + "invoke.");
            return invoker.invoke(invocation);
        }

    }

    static class PrintInvoker implements Invoker {
        @Override
        public Result invoke(Invocation invocation) {
            invocation.printNumber();
            return new PrintResult("PrintInvoker done!");
        }
    }

    @RequiredArgsConstructor
    @ToString
    static class PrintResult implements Result {
        private final String message;

    }
}
