import java.lang.instrument.*;

class Tracer {
    public static void premain(String args, Instrumentation inst) {
        inst.addTransformer(new Transformer());
    }
}
