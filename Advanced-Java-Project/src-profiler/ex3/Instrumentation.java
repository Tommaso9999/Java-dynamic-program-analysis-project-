package ex3;

import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.marker.BodyMarker;

public class Instrumentation {

    // After the method checkAccess, check if the local variable at the position 2 of the operand stack is equal to "granted".
    // Print the Thread executing at that moment.
    @After(marker = BodyMarker.class,
            scope = "ex3.MainThread.checkAccess")
    static void detectUpdate(DynamicContext dc){
        if (dc.getLocalVariableValue(2, String.class).equals("granted")) {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
