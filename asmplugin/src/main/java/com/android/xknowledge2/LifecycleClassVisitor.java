package com.android.xknowledge2;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;

public class LifecycleClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;

    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
        System.out.println("LifecycleClassVisitor : LifecycleClassVisitor ");
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //访问某个类
        System.out.println("LifecycleClassVisitor : visit " + "version = " + version + ",access = " + access + ",name = "
                + name + ",signature = " + signature + ",superName = " + superName + ",interfaces = " + Arrays.toString(interfaces));
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(String source, String debug) {
        System.out.println("LifecycleClassVisitor : visitSource " + "source = " + source + ",debug = " + debug);
        super.visitSource(source, debug);
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        super.visitOuterClass(owner, name, descriptor);
        System.out.println("LifecycleClassVisitor : visitOuterClass " + "owner = " + owner + ",name = " + name + ",descriptor = " + descriptor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("LifecycleClassVisitor : visitAnnotation " + "descriptor = " + descriptor + ",visible = " + visible);
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
        System.out.println("LifecycleClassVisitor : visitAttribute " + "attribute = " + attribute.toString());
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        super.visitInnerClass(name, outerName, innerName, access);
        System.out.println("LifecycleClassVisitor : visitInnerClass " + "name = " + name + ",outerName = " + outerName +
                ",innerName = " + innerName + ",access = " + access);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("LifecycleClassVisitor : visitField " + "access = " + access + ",name = " + name +
                ",descriptor = " + descriptor + ",signature = " + signature + ",value = " + value);
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        //访问类里面的方法
        System.out.println("LifecycleClassVisitor : visitMethod " + "access = " + access + ",name = " + name + ",desc = "
                + desc + ",signature = " + signature + ",exceptions = " + Arrays.toString(exceptions));
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        //匹配FragmentActivity
        if ("androidx/fragment/app/FragmentActivity".equals(this.mClassName)) {
            if ("onCreate".equals(name)) {
                //处理onCreate
                System.out.println("LifecycleClassVisitor : change method ----> " + name);
                return new LifecycleOnCreateMethodVisitor(mv);
            } else if ("onDestroy".equals(name)) {
                //处理onDestroy
                System.out.println("LifecycleClassVisitor : change method ----> " + name);
                return new LifecycleOnDestroyMethodVisitor(mv);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        System.out.println("LifecycleClassVisitor : visitEnd ");
        super.visitEnd();
    }
}
