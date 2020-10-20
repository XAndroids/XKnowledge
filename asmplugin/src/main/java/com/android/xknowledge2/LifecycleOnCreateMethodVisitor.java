package com.android.xknowledge2;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;

import java.util.Arrays;

public class LifecycleOnCreateMethodVisitor extends MethodVisitor {

    public LifecycleOnCreateMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
        System.out.println("LifecycleOnCreateMethodVisitor : LifecycleOnCreateMethodVisitor ");
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
        System.out.println("LifecycleOnCreateMethodVisitor : visitAttribute " + "attribute = " + attribute.toString());
    }

    //表示ASM开始扫描这个方法
    //参考：https://my.oschina.net/ta8210/blog/220011
    @Override
    public void visitCode() {
        super.visitCode();
        System.out.println("LifecycleOnCreateMethodVisitor : visitCode ");
        //方法执行前插入
        //使用 ASM-Bytecode 工具，将这段代码转变为 ASM 代码。
        //参考：https://my.oschina.net/ta8210/blog/162796
        mv.visitLdcInsn("TAG");
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("-------> onCreate asm success : ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(Opcodes.POP);
    }

    @Override
    public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
        super.visitFrame(type, numLocal, local, numStack, stack);
        System.out.println("LifecycleOnCreateMethodVisitor : visitFrame " + "type = " + type + ", numLocal = " + numLocal
                + ", local = " + Arrays.toString(local) + ", numStack = " + numStack + ",stack = " + Arrays.toString(stack));
    }

    @Override
    public void visitInsn(int opcode) {
        //方法执行后插入
        /*if (opcode == Opcodes.RETURN) {
            mv.visitLdcInsn("TAG");
            mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
            mv.visitInsn(Opcodes.DUP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            mv.visitLdcInsn("-------> onCreate : end ：");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false);
            mv.visitInsn(Opcodes.POP);
        }*/
        super.visitInsn(opcode);
        System.out.println("LifecycleOnCreateMethodVisitor : visitInsn " + "opcode = " + opcode);
    }

    @Override
    public void visitIntInsn(int opcode, int operand) {
        super.visitIntInsn(opcode, operand);
        System.out.println("LifecycleOnCreateMethodVisitor : visitIntInsn " + "opcode = " + opcode + ",operand = " + operand);
    }

    @Override
    public void visitVarInsn(int opcode, int var) {
        super.visitVarInsn(opcode, var);
        System.out.println("LifecycleOnCreateMethodVisitor : visitVarInsn " + "opcode = " + opcode + ",var = " + var);
    }

    @Override
    public void visitTypeInsn(int opcode, String type) {
        super.visitTypeInsn(opcode, type);
        System.out.println("LifecycleOnCreateMethodVisitor : visitTypeInsn " + "opcode = " + opcode + ",type = " + type);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        super.visitFieldInsn(opcode, owner, name, descriptor);
        System.out.println("LifecycleOnCreateMethodVisitor : visitFieldInsn " + "opcode = " + opcode +
                ",owner = " + owner + ", name  = " + name + ", descriptor = " + descriptor);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
        System.out.println("LifecycleOnCreateMethodVisitor : visitMethodInsn " + "opcode = " + opcode +
                ",owner = " + owner + ", name  = " + name + ", descriptor = " + descriptor + ", isInterface =" + isInterface);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle,
                                       Object... bootstrapMethodArguments) {
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
        System.out.println("LifecycleOnCreateMethodVisitor : visitInvokeDynamicInsn " + "name = " + name +
                ",descriptor = " + descriptor + ", bootstrapMethodHandle  = " + bootstrapMethodHandle.toString() +
                ", bootstrapMethodArguments = " + Arrays.toString(bootstrapMethodArguments));
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
        System.out.println("LifecycleOnCreateMethodVisitor : visitJumpInsn " + "opcode = " + opcode + ",label = " + label);
    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(label);
        System.out.println("LifecycleOnCreateMethodVisitor : visitLabel " + "label = " + label);
    }

    @Override
    public void visitLdcInsn(Object value) {
        super.visitLdcInsn(value);
        System.out.println("LifecycleOnCreateMethodVisitor : visitLdcInsn " + "value = " + value);
    }

    @Override
    public void visitIincInsn(int var, int increment) {
        super.visitIincInsn(var, increment);
        System.out.println("LifecycleOnCreateMethodVisitor : visitIincInsn " + "var = " + var + ",increment = " + increment);
    }

    @Override
    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        super.visitTableSwitchInsn(min, max, dflt, labels);
        System.out.println("LifecycleOnCreateMethodVisitor : visitTableSwitchInsn " + "min = " + min +
                ",dflt = " + dflt + ", labels = " + Arrays.toString(labels));
    }

    @Override
    public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
        super.visitMultiANewArrayInsn(descriptor, numDimensions);
        System.out.println("LifecycleOnCreateMethodVisitor : visitMultiANewArrayInsn " + "descriptor = " + descriptor
                + ",numDimensions = " + numDimensions);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        super.visitTryCatchBlock(start, end, handler, type);
        System.out.println("LifecycleOnCreateMethodVisitor : visitTryCatchBlock " + "start = " + start
                + ",end = " + end  + ", handler = " + handler.toString() + ",type = " + type);
    }

    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
        System.out.println("LifecycleOnCreateMethodVisitor : visitLocalVariableAnnotation" + " typeRef = " + typeRef +
                ", typePath = " + typePath.toString() + ", start = " + Arrays.toString(start) + ", end = " + Arrays.toString(end)
                + ", index = " + Arrays.toString(index) + ", descriptor = " + descriptor + ", visible = " + visible);
        return super.visitLocalVariableAnnotation(typeRef, typePath, start, end, index, descriptor, visible);
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        super.visitLineNumber(line, start);
        System.out.println("LifecycleOnCreateMethodVisitor : visitLineNumber" + " line = " + line +
                ", start = " + start);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitMaxs(maxStack, maxLocals);
        System.out.println("LifecycleOnCreateMethodVisitor : visitMaxs" + " maxStack = " + maxStack +
                ", maxLocals = " + maxLocals);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println("LifecycleOnCreateMethodVisitor : visitEnd");
    }
}
