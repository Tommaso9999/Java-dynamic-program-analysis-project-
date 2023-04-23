package ex6;

import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.marker.BasicBlockMarker;
import ch.usi.dag.disl.staticcontext.LoopStaticContext;
import ch.usi.dag.disl.staticcontext.MethodStaticContext;

public class Instrumentation {

    // Increment counter after each Loop Block
    @After(marker = BasicBlockMarker.class,
            scope = "ex6.MainThread.*")
    static void afterLoopBlock(MethodStaticContext msc, LoopStaticContext lsc) {
        if (lsc.isFirstOfLoop()) {
            Profiler.profileAllocation(msc.thisMethodFullName());
        }
    }
}
