package ex5;

import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.marker.BytecodeMarker;

public class Instrumentation {

    // Register safe accesses after every bytecode instruction.
    @After(marker = BytecodeMarker.class,
            args = "new, newarray, anewarray, multianewarray")
    static void safeAllCount() {Profiler.registerSafeAccesses();}

    // Register unsafe accesses done by the method allocateInstance
    @After(marker = BodyMarker.class,
            scope = "allocateInstance")
    static void unsafeAllCount() {Profiler.registerUnsafeAccesses();}
}
