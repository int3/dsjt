import org.objectweb.asm.*;

class ClassAdapter extends ClassVisitor {

    public ClassAdapter(int api, ClassVisitor cv) {
        super(api, cv);
    }

    String className;

    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        className = name;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
            String desc, String signature, String[] exceptions) {

        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

        if ((access & Opcodes.ACC_ABSTRACT) == 0) {
            mv.visitFieldInsn(
                Opcodes.GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;"
            );
            mv.visitLdcInsn(className + "." + name + desc);
            mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V"
            );
        }

        return mv;
    }

}
