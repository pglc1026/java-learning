package jl.instrumentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Properties;


public class InstrumentationHolder {

    public static final String default_config_path = "/opt/java-agent.properties";
    public static Instrumentation instrumentation = null;

    public static void premain(String agentArgs, Instrumentation instrumentationTemp) {
        instrumentation = instrumentationTemp;
        System.out.println("[premain][init ]:agentArgs=" + agentArgs + ",instrumentationName=" + instrumentationTemp.getClass().getName());
        Class[] allLoadedClasses = instrumentationTemp.getAllLoadedClasses();
        for (Class _class : allLoadedClasses) {
            System.out.println("[premain][LoadedClasses]:className=" + _class.getName());
        }
        Properties properties = new Properties();
        try {
            if (agentArgs != null && agentArgs.length() != 0) {
                System.out.println("[premain][read ]:config_path=" + agentArgs);
                properties.load(new FileInputStream(agentArgs));
            } else {
                System.out.println("[premain][read ]:default_config_path=" + default_config_path);
                properties.load(new FileInputStream(default_config_path));
            }

        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                try {
                    properties.load(new FileInputStream(default_config_path));
                } catch (IOException e1) {
                    System.out.println(
                            "[premain][error]:how to use:java -javaagent:{1}={2}  {1} is agent jar ,{2} is conf properties , default {2} is /opt/java-agent.properties");
                    System.out.println(
                            "[premain][error]:/opt/java-agent.properties for example sun/security/util/HostnameChecker=C:/Users/paul/Desktop/HostnameChecker.class");
                }
            } else {
                e.printStackTrace();
            }
        }
        if (properties.isEmpty()) {
            System.out.println("[premain][info ]:config properties is empty,so do not transform Class");
            return;
        }
        instrumentationTemp.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                    byte[] classfileBuffer) throws IllegalClassFormatException {
                //className 对于类     :java/lang/Void
                //className 对于内部类 :java/lang/Class$MethodArray
                String property = properties.getProperty(className);
                if (property != null) {
                    byte[] fileBytes = getFileBytes(property);
                    if (fileBytes != null) {
                        System.out.println("[premain][replace]:className=" + className + ",fileName=" + property);
                        return fileBytes;
                    }
                }
                return null;
            }
        }, true);
    }

    public static byte[] getFileBytes(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return null;
            }
            long fileSize = file.length();
            FileInputStream fi = new FileInputStream(file);
            byte[] buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset != buffer.length) {
                return null;
            }
            fi.close();
            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
