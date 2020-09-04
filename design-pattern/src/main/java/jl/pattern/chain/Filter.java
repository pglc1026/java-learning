package jl.pattern.chain;

/**
 * Filter
 *
 * @author Liu Chang
 * @date 2020/6/6
 */
public interface Filter {

    Result invoke(Invoker invoker, Invocation invocation);

}
