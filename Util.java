import org.objectweb.asm.*;

class Util {
    static void insertPrintInsns(MethodVisitor mv, String s) {
        mv.visitFieldInsn(
            Opcodes.GETSTATIC,
            "java/lang/System",
            "out",
            "Ljava/io/PrintStream;"
        );
        mv.visitLdcInsn(s);
        mv.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            "java/io/PrintStream",
            "println",
            "(Ljava/lang/String;)V"
        );
    }
}
