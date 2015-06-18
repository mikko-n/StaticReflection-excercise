package staticreflection;

public class ClassToTest {
    private static char staticChar = '!';
    private char regularChar = '?';
    
    public ClassToTest() {}
    
    public void publicMethod(DataTypeEnum controlOption) {
        
        switch (controlOption) {
            case OPTION1:                
                // do something
                System.out.println("Did something with Option 1");
                break;                
            case OPTION2:
                // before doing something, set controlling characters
                staticChar = '-';
                regularChar = '=';
                // do something
                System.out.println("Did something with Option 2");
                break;                
            case OPTION3:
                // do something
                System.out.println("Did something with Option 1");
                break;
        }
    }        
}


