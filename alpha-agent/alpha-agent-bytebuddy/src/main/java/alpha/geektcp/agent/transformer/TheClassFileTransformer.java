package alpha.geektcp.agent.transformer;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @author haiyang.tang on 11.14 014 16:29:45.
 */
public class TheClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)  {
        System.out.println("start agent !");
        if (!className.startsWith("com/geektcp")) {
            return null;
        }
        String newClassName = className.replace("/", ".");
        System.out.println("Transforming: " + newClassName);

        ClassPool pool = ClassPool.getDefault();
        CtClass cl = null;
        try {
            pool.insertClassPath(new LoaderClassPath(loader));
            try {
                cl = pool.get(newClassName);
            } catch (NotFoundException e) {
                ByteArrayInputStream is = null;
                try {
                    is = new ByteArrayInputStream(classfileBuffer);
                    cl = pool.makeClass(is);
                } finally {
                    if (null != is) {
                        is.close();
                        is = null;
                    }
                }
            }

            if (cl.isInterface()) {
                return null;
            }

            CtMethod[] methods = cl.getDeclaredMethods();
            for (CtMethod method : methods) {
                System.out.println("=====" + method.getName());
//                enhance(method);
                enhanceMethod(method);
            }
            return cl.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != cl) {
                cl.detach();
            }
        }
    }

    private void enhance(CtMethod method) throws CannotCompileException {
        method.insertBefore("{ System.out.println(\"" + method.getLongName() + " called ...\"); }");
        method.instrument(new ExprEditor() {
            public void edit(MethodCall m) throws CannotCompileException {
                String className = m.getClassName();
                System.out.println("className: " + className);
                System.out.println("getMethodName: " + m.getMethodName());
                if (className.startsWith("com.geektcp.dingtalk.controller")) {
                    StringBuilder source = new StringBuilder();
                    source.append("{long startTime = System.currentTimeMillis();");
                    source.append("$_=$proceed($$) ;");
                    source.append("System.out.println(\"");
                    source.append(className).append(".").append(m.getMethodName());
                    source.append(" cost: \" + (System.currentTimeMillis() - startTime) + \" ms\");}");
                    m.replace(source.toString());
                }
            }
        });
        System.out.println("agent enhanced!");
    }

    private void enhanceMethod(CtMethod method) throws Exception {
        method.insertBefore("{ System.out.println(\"" + method.getLongName() + " called ...\"); }");
        if (method.isEmpty()) {
            return;
        }
        String methodName = method.getName();
        if (methodName.equalsIgnoreCase("main")) { // 不提升main方法
            return;
        }


        // 统计接口内部的方法的执行时长
        ExprEditor editor = new ExprEditor() {
            @Override
            public void edit(MethodCall methodCall) throws CannotCompileException {
                String className = methodCall.getClassName();
                System.out.println("<<<<<<<<<<<<<<");
                System.out.println("className: " + className);
                System.out.println("getMethodName: " + methodCall.getMethodName());
                StringBuilder source = new StringBuilder();
                source.append("{long startTime = System.currentTimeMillis();");
                source.append("$_=$proceed($$);");
                source.append("System.out.println(\"");
                source.append("[className: " + className + "]").append(".")
                        .append("[method:" + methodCall.getMethodName() + "]");
                source.append(" cost: \" + (System.currentTimeMillis() - startTime) + \" ms\");}");
                methodCall.replace(source.toString());

                System.out.println(">>>>>>>>>>>>>");
            }
        };
        method.instrument(editor);
    }
}