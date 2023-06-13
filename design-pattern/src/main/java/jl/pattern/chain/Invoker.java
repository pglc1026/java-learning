package jl.pattern.chain;

/**
 * Invoker
 *
 * @author Liu Chang
 * @date 2020/6/6
 */
public interface Invoker {

    Result invoke(Invocation invocation);

}
