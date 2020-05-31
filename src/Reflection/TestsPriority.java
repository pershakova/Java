package Reflection;

public class TestsPriority {
    @BeforeSuite
    public void initialize() {
        System.out.println("initialize");
    }

    @Test(priority = 6)
    public void testPriority6() {
        System.out.println("testPriority5");
    }

    @Test(priority = 2)
    public void testPriority2() {
        System.out.println("testPriority2");
    }

    @Test(priority = 1)
    public void testPriority1() {
        System.out.println("testPriority1");
    }

    @AfterSuite
    public void cleanUp() {
        System.out.println("cleanUp");
    }
}
