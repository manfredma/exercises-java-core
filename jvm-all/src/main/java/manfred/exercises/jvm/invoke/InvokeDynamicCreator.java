package manfred.exercises.jvm.invoke;

import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.invoke.*;

/**
 * 使用 ASM 手工生成含有 invokedynamic 指令的字节码，演示动态调用点的完整创建流程。
 *
 * 通过 ASM ClassWriter 构建一个类，其 main 方法中包含 invokedynamic 指令，
 * 引导方法（bootstrap method）在运行时将动态调用点绑定到具体的 MethodHandle，
 * 用于深入理解 Lambda、方法引用等语言特性背后 JVM 动态调用机制的实现原理（JSR-292）。
 */
public class InvokeDynamicCreator {

    public static void main(final String[] args) throws Exception {
        final String outputClassName = "kathik/Dynamic";
        try (FileOutputStream fos
                = new FileOutputStream(new File("target/classes/" + outputClassName + ".class"))) {
            fos.write(dump(outputClassName, "bootstrap", "()V"));
        }
    }

    public static byte[] dump(String outputClassName, String bsmName, String targetMethodDescriptor)
            throws Exception {
        final ClassWriter cw = new ClassWriter(0);
        MethodVisitor mv;

        // 为引导类搭建基本的元数据
        cw.visit(V1_7, ACC_PUBLIC + ACC_SUPER, outputClassName, null, "java/lang/Object", null);

        // 创建标准的void构造器
        mv = cw.visitMethod(ACC_PUBLIC, "", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "", "()V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        // 创建标准的main方法
        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        MethodType mt = MethodType.methodType(CallSite.class, MethodHandles.Lookup.class, String.class,
                MethodType.class);
        Handle bootstrap = new Handle(Opcodes.H_INVOKESTATIC, "kathik/InvokeDynamicCreator", bsmName,
                mt.toMethodDescriptorString());
        mv.visitInvokeDynamicInsn("runDynamic", targetMethodDescriptor, bootstrap);
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 1);
        mv.visitEnd();

        cw.visitEnd();

        return cw.toByteArray();
    }

    private static void targetMethod() {
        System.out.println("Hello World!");
    }

    public static CallSite bootstrap(MethodHandles.Lookup caller, String name, MethodType type) throws NoSuchMethodException, IllegalAccessException {
        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        // 需要使用lookupClass()，因为这个方法是静态的
        final Class currentClass = lookup.lookupClass();
        final MethodType targetSignature = MethodType.methodType(void.class);
        final MethodHandle targetMH = lookup.findStatic(currentClass, "targetMethod", targetSignature);
        return new ConstantCallSite(targetMH.asType(type));
    }
} 