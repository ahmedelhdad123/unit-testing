package org.example;

public class Test {
    int pass,failed,count;
    public void test(String name,Runnable runnable) {
        try {
            runnable.run();
            pass++;
        }catch (Exception e)
        {
            System.out.println("Test is Failed :"+name+ " " +e.getMessage());
            failed++;
        }
        count++;
    }
    public void printSummary()
    {
        System.out.println("pass is : "+pass+ " failed is : "+failed + " All Operation is : " +count);
    }
}
