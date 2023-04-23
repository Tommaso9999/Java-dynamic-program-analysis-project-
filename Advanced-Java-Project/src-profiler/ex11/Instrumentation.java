package ex11;

import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.staticcontext.ClassStaticContext;
import ch.usi.dag.disl.staticcontext.InstructionStaticContext;
import ch.usi.dag.disl.staticcontext.InvocationStaticContext;
import ch.usi.dag.disl.staticcontext.MethodStaticContext;

import ch.usi.dag.disl.annotation.*;

public class Instrumentation {

    // Before invokevirtual annotation
    @Before(marker = BytecodeMarker.class,
            args = "invokevirtual",
            scope ="ex11.Main.*")
    static void afterStatic(DynamicContext dc, MethodStaticContext msc, InstructionStaticContext isc, ClassStaticContext csc, InvocationStaticContext ivc) {
        // The variable type stores the type of invocation
        String type = "invokevirtual";

        // Instantiate the Class Loader to get information
        ClassLoader cl = Thread.currentThread().getContextClassLoader();

        // Try and Catch to avoid ClassNotFoundException
        try {
            // Load the class from Dynamic Context
            Class<?> aClass =   cl.loadClass(dc.getStackValue(0, String.class).getClass().getName());

            // Call the profiler method to build the string format
            Profiler.ex11virtualMap(msc.getUniqueInternalName(),
                    isc.getIndex(), ivc.getUniqueInternalName(), aClass, type);
        } catch (ClassNotFoundException e) {
        }
    }

    // Before invokeinterface annotation
    @Before(marker = BytecodeMarker.class,
            args = "invokeinterface",
            scope ="ex11.Main.*")
    static void afterinterface(DynamicContext dc, MethodStaticContext msc, InstructionStaticContext isc, ClassStaticContext csc, InvocationStaticContext ivc) {

        // The variable type stores the type of invocation
        String type = "invokeinterface";

        // Call the profiler method to build the string format
        Profiler.ex11Map(msc.getUniqueInternalName(),
                isc.getIndex(), ivc.getUniqueInternalName(), ivc.getUniqueInternalName(), type);
    }

    // Before invokespecial annotation
    @Before(marker = BytecodeMarker.class,
            args = "invokespecial",
            scope ="ex11.Main.*")
    static void afterspecial(DynamicContext dc, MethodStaticContext msc, InstructionStaticContext isc, ClassStaticContext csc, InvocationStaticContext ivc) {

        // The variable type stores the type of invocation
        String type = "invokespecial";

        // Call the profiler method to build the string format
        Profiler.ex11Map(msc.getUniqueInternalName(),
                isc.getIndex(), ivc.getUniqueInternalName(), ivc.getUniqueInternalName(), type);
    }

    // Before invokestatic annotation
    @Before(marker = BytecodeMarker.class,
            args = "invokestatic",
            scope ="ex11.Main.*")
    static void afterStatico(DynamicContext dc, MethodStaticContext msc, InstructionStaticContext isc, ClassStaticContext csc, InvocationStaticContext ivc) {
        // The variable type stores the type of invocation
        String type = "invokestatic";

        // Call the profiler method to build the string format
        Profiler.ex11Map(msc.getUniqueInternalName(),
                isc.getIndex(), ivc.getUniqueInternalName(), ivc.getUniqueInternalName(), type);
    }

    // Before invokedynamic annotation
    @Before(marker = BytecodeMarker.class,
            args = "invokedynamic",
            scope ="ex11.Main.*")
    static void afterdynamic(DynamicContext dc, MethodStaticContext msc, InstructionStaticContext isc, ClassStaticContext csc, InvocationStaticContext ivc) {
        // The variable type stores the type of invocation
        String type = "invokedynamic";

        // Call the profiler method to build the string format
        Profiler.ex11Map(msc.getUniqueInternalName(),
                isc.getIndex(), ivc.getUniqueInternalName(), ivc.getUniqueInternalName(), type);
    }
}