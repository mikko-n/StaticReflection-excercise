package staticreflection;

import java.lang.reflect.Field;

/**
 * What?
 * Small demo program to highlight the differences between static and non-static
 * field behavior in not-so-obvious situation where the field is private
 * to a class.
 * 
 * Why? 
 * Static fields are visible per classloader and thus, not related to a particular 
 * class instance at all. In other words, you can use the static field in 
 * class A without ever creating any instances of it.
 * 
 * When class itself is not static and we are dealing with private static/non-static
 * fields, there is a catch: static fields inside a (non-static) class are still
 * shared between all instances of given class, when they are loaded with 
 * the same JVM classloader.
 *
 * In certain situations this might give some headaches, especially when classes
 * with private static fields get re-used.
 * 
 * Another reason to write this was to refresh my java reflection superpowers.
 * 
 * tl;dr; static field is per class, not per class instance
 *
 */
public class StaticReflection {    
    
    public static void main(String[] args) {        
        // scenarios
        classReuseScenario();
        classSingleUseScenario();
    }

    private static void classReuseScenario() {        
        System.out.println("\n** class instance reuse scenario **\n");
        
        // here we create class which is reused between publicMethod() calls
        ClassToTest test = new ClassToTest();
        
        // go through enum values
        for (DataTypeEnum item : DataTypeEnum.values()) {            
            System.out.println("Called publicMethod with parameter: "+ item);
            test.publicMethod(item);
            
            // get private field values from (re-used) class instance via reflection
            try {
                Field staticCharField = ClassToTest.class.getDeclaredField("staticChar");
                staticCharField.setAccessible(true);
                System.out.println("staticChar value: "+ staticCharField.getChar(test));
                
                Field regularCharField = ClassToTest.class.getDeclaredField("regularChar");
                regularCharField.setAccessible(true);
                System.out.println("regularChar value: "+ regularCharField.getChar(test));
            } catch (Exception e) {
                // oops
                e.printStackTrace();
            }
        }
    }
    
    private static void classSingleUseScenario() {
        
        System.out.println("\n** class instance recreation scenario **\n");
        // go through enum values
        for (DataTypeEnum item : DataTypeEnum.values()) {   
            
            // here we create new class instance for each publicMethod() call,
            // this resets the private non-static fields to class default values
            // and the class loader provides the stored static values to class
            ClassToTest test = new ClassToTest();
            
            System.out.println("Called publicMethod with parameter: "+ item);
            test.publicMethod(item);
            
            // get private field values from new class instance via reflection
            try {
                Field staticCharField = ClassToTest.class.getDeclaredField("staticChar");
                staticCharField.setAccessible(true);
                System.out.println("staticChar value: "+ staticCharField.getChar(test));
                
                Field regularCharField = ClassToTest.class.getDeclaredField("regularChar");
                regularCharField.setAccessible(true);
                System.out.println("regularChar value: "+ regularCharField.getChar(test));
            } catch (Exception e) {
                // oops
                e.printStackTrace();
            }
        }
    }
}
