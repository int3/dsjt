import java.lang.instrument.*;
import java.security.*;
import org.objectweb.asm.*;

public class Transformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> clazz,
                            ProtectionDomain pd, byte[] buffer) {
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor visitor = new ClassAdapter(Opcodes.ASM4, writer);
        ClassReader reader = new ClassReader(buffer);
        reader.accept(visitor, 0);
        return writer.toByteArray();
    }

}
