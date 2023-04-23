package ex10;

import ch.usi.dag.disl.annotation.AfterReturning;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.staticcontext.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Instrumentation {

    // Player Methods
    @AfterReturning(marker = BodyMarker.class,
                    scope = "ex10.Player.*")
    static void getPlayerInfo(ClassStaticContext csc, MethodStaticContext msc){

        // Create Class Loader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Try/Catch for ClassNotFound Exception
        try {
            // Load class from ClassStaticContext
            Class<?> player = classLoader.loadClass(csc.getName());

            // Create arrays to store methods (declared and all)
            Method[] methodsArray = player.getMethods();
            Method[] decMethodsArray = player.getDeclaredMethods();

            // Format Executed Methods
            Profiler.getMethods(csc.getName(), "Executed: " + msc.thisMethodFullName());

            // Loop on all methods to determine Declared and Inherited
            for (Method me : methodsArray){

                //Create arrays for parameter types and exception types
                Class<?>[] paramTypes = me.getParameterTypes();
                Class<?>[] exceptionTypes = me.getExceptionTypes();

                // Create Strings to contain parameter types and exception types
                String pType = "";
                String eType = "";

                // Loop to get the parameter types matched with right method
                for (Class<?> paramType : paramTypes){
                    if (pType.equals("")) {
                        pType += paramType.getName();
                    } else {
                        pType += "," + paramType.getName();
                    }
                }

                // Loop to get the exception types matched with right method
                for (Class<?> exceptionThrown : exceptionTypes){
                    if (eType.equals("")) {
                        eType += " throws " + exceptionThrown.getName();
                    } else {
                        eType += ", " + exceptionThrown.getName();
                    }
                }

                // If/Else Structure to get Declared Methods else Inherited Methods
                if (Arrays.asList(decMethodsArray).contains(me))  {
                    Profiler.getMethods(csc.getName(), "Declared: " + Modifier.toString(me.getModifiers()) + " " +
                                                                                                  me.getReturnType() + " " +
                                                                                                  player.getName() + "." + me.getName() + "("+ pType +")");
                }
                else {
                    Profiler.getMethods(csc.getName(), "Inherited: " + Modifier.toString(me.getModifiers()) + " " +
                                                                                                   me.getReturnType() + " " +
                                                                                                   player.getName() + "." + me.getName() +
                                                                                                   "(" + pType + ")" +
                                                                                                    eType);
                }
            }
        } catch (ClassNotFoundException e){
        }
    }

    // VideoGamePlayer Methods
    @AfterReturning(marker = BodyMarker.class,
            scope = "ex10.VideoGamePlayer.*")
    static void getVideoGamePlayerInfo(ClassStaticContext csc, MethodStaticContext msc){

        // Create Class Loader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Try/Catch for ClassNotFound Exception
        try {
            // Load class from ClassStaticContext
            Class<?> player = classLoader.loadClass(csc.getName());

            // Create arrays to store methods (declared and all)
            Method[] methodsArray = player.getMethods();
            Method[] decMethodsArray = player.getDeclaredMethods();

            // Format Executed Methods
            Profiler.getMethods(csc.getName(), "Executed: " + msc.thisMethodFullName());

            // Loop on all methods to determine Declared and Inherited
            for (Method me : methodsArray){

                //Create arrays for parameter types and exception types
                Class<?>[] paramTypes = me.getParameterTypes();
                Class<?>[] exceptionTypes = me.getExceptionTypes();

                // Create Strings to contain parameter types and exception types
                String pType = "";
                String eType = "";

                // Loop to get the parameter types matched with right method
                for (Class<?> paramType : paramTypes){
                    if (pType.equals("")) {
                        pType += paramType.getName();
                    } else {
                        pType += "," + paramType.getName();
                    }
                }

                // Loop to get the exception types matched with right method
                for (Class<?> exceptionThrown : exceptionTypes){
                    if (eType.equals("")) {
                        eType += " throws " + exceptionThrown.getName();
                    } else {
                        eType += ", " + exceptionThrown.getName();
                    }
                }

                // If/Else Structure to get Declared Methods else Inherited Methods
                if (Arrays.asList(decMethodsArray).contains(me))  {
                    Profiler.getMethods(csc.getName(), "Declared: " + Modifier.toString(me.getModifiers()) + " " +
                            me.getReturnType() + " " +
                            player.getName() + "." + me.getName() + "("+ pType +")");
                }
                else {
                    Profiler.getMethods(csc.getName(), "Inherited: " + Modifier.toString(me.getModifiers()) + " " +
                            me.getReturnType() + " " +
                            player.getName() + "." + me.getName() +
                            "(" + pType + ")" +
                            eType);
                }
            }
        } catch (ClassNotFoundException e){
        }
    }


}

