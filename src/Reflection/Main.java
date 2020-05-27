package Reflection;

public class Main {
    public static void main(String[] args) {
        TestsPriority classTestsPriority = new TestsPriority();
        TestsHandler.start(classTestsPriority.getClass());
    }
}
