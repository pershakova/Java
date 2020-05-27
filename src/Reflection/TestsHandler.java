package Reflection;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class TestsHandler {

    public static void start(Class testClass) {
        if (!areAnnotationsUnique(testClass)) {
            throw new RuntimeException("Annotations are not unique");
        }

        Object instance = createInstance(testClass);

        ArrayList<Method> testMethods =  getSortedMethods(testClass);

        invokeMethods(instance, testMethods);
    }

    private static boolean areAnnotationsUnique(Class testClass) {
        int beforeAnnotation = 0;
        int afterAnnotation = 0;

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeAnnotation++;
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                afterAnnotation++;
            }
        }

        return (beforeAnnotation < 2) && (afterAnnotation < 2);
    }

    private static Object createInstance(Class testClass){
        try {
            return testClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ArrayList<Method> getSortedMethods(Class testClass){
        Method[] objMethods = testClass.getDeclaredMethods();

        Method beforeAnnotation = null;
        Method afterAnnotation = null;
        ArrayList<Method> testMethods = new ArrayList<>();

        for (Method method : objMethods) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                beforeAnnotation = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                afterAnnotation = method;
            } else if (method.getAnnotation(Test.class) != null) {
                testMethods.add(method);
            }
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));

        if (beforeAnnotation != null) {
            testMethods.add(0, beforeAnnotation);
        }

        if (afterAnnotation != null) {
            testMethods.add(afterAnnotation);
        }

        return testMethods;
    }

    private static void invokeMethods(Object instance, ArrayList<Method> methods){
        try {
            for (Method testMethod : methods) {
                testMethod.invoke(instance);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
