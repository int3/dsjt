import org.objectweb.asm.*;
import java.util.HashSet;

class PrintMethodReturnAdapter extends MethodVisitor {
    String methodSignature;
    HashSet<Label> catchHandlers;

    public PrintMethodReturnAdapter(MethodVisitor mv, String methodSignature) {
        super(Opcodes.ASM4, mv);
        this.methodSignature = methodSignature;
        catchHandlers = new HashSet<Label>();
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN
                || opcode == Opcodes.IRETURN
                || opcode == Opcodes.ARETURN
                || opcode == Opcodes.DRETURN) {
            Util.insertPrintInsns(mv, "Returning from " + methodSignature);
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        catchHandlers.add(handler);
        mv.visitTryCatchBlock(start, end, handler, type);
    }

    @Override
    public void visitLabel(Label label) {
        mv.visitLabel(label);
        if (catchHandlers.contains(label))
            Util.insertPrintInsns(mv, "Caught exception in " + methodSignature);
    }
}
