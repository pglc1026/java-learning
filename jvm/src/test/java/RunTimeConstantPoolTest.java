import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * RunTimeConstantPoolTest
 *
 * @author pglc1026
 * @date 2019-05-07
 */
public class RunTimeConstantPoolTest {

    @Test
    public void testRunTimeConstantPool() throws Throwable {
        List<String> list = new ArrayList<String>();
        int i=0;
        while(true){
            System.out.println("==========" + i);
            list.add(String.valueOf(i++).intern());
        }

    }

    @Test
    public void testMetaOOM() throws Exception {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy)
                        throws Throwable {
                    return methodProxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {

    }

}
