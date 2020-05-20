package jl.instrumentation;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;

/**
 * @author paul
 * @description
 * @date 2018/11/12
 */
public class InstrumentationTest {

    public static Instrumentation getInstrumentation() {
        try {
            Class<?> aClass = Class.forName("com.paulzhangcc.InstrumentationHolder");
            Field instrumentation = aClass.getField("instrumentation");
            return (Instrumentation) instrumentation.get(null);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        Instrumentation instrumentation = getInstrumentation();
        if (instrumentation != null) {
            // 查看Test对象的大小
            long objectSize = instrumentation.getObjectSize(new InstrumentationTest());
            System.out.println("Object Test size is " + objectSize + " Byte");
        }
    }
}