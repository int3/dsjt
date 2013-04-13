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

        String methodSignature = className + "." + name + desc;

        if ((access & Opcodes.ACC_ABSTRACT) == 0) {
            Util.insertPrintInsns(mv, "Entering " + methodSignature);
        }

        return new PrintMethodReturnAdapter(mv, methodSignature);
    }

}
