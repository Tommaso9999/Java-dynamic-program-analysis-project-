package ex7;

import ch.usi.dag.disl.annotation.GuardMethod;
import ch.usi.dag.disl.staticcontext.MethodStaticContext;

public class SynchronizedGuard {

    // Instantiate Synchronized methods guard
    @GuardMethod
    public static boolean isSynchronized(MethodStaticContext msc){
        return msc.isMethodSynchronized();
    }
}
