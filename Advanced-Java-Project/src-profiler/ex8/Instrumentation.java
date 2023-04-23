package ex8;

import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.marker.BytecodeMarker;

public class Instrumentation {

    // Call profiler method after "invokestatic" instruction
    @After(marker = BytecodeMarker.class,
            args = "invokestatic")
    static void afterStatic(DynamicContext dc) {
        Profiler.incrementStatic(Thread.currentThread().getName());
    }

    // Call profiler method after "invokespecial" instruction
    @After(marker = BytecodeMarker.class,
            args = "invokespecial")
    static void afterSpecial(DynamicContext dc) {
        Profiler.incrementSpecial(Thread.currentThread().getName());
    }

    // Call profiler method after "invokevirtual" instruction
    @After(marker = BytecodeMarker.class,
            args = "invokevirtual")
    static void afterVirtual(DynamicContext dc) {
        Profiler.incrementVirtual(Thread.currentThread().getName());
    }

    // Call profiler method after "invokedynamic" instruction
    @After(marker = BytecodeMarker.class,
            args = "invokedynamic")
    static void afterDynamic(DynamicContext dc) {
        Profiler.incrementDynamic(Thread.currentThread().getName());
    }}