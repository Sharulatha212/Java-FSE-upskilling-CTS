import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class ReflectionDemo {
    public static void main(String[] args) {
        try {
            // Load the class dynamically
            Class<?> demoClass = Class.forName("DecompileDemo");
            System.out.println("Class: " + demoClass.getName());

            // Print all declared methods
            System.out.println("\nDeclared Methods:");
            Method[] methods = demoClass.getDeclaredMethods();
            for (Method method : methods) {
                // Get method modifiers (public, private, etc.)
                String modifiers = Modifier.toString(method.getModifiers());

                // Get return type
                String returnType = method.getReturnType().getSimpleName();

                // Get method name
                String methodName = method.getName();

                // Get parameters
                Parameter[] parameters = method.getParameters();
                String params = Arrays.stream(parameters)
                        .map(p -> p.getType().getSimpleName() + " " + p.getName())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("");

                System.out.printf("%s %s %s(%s)%n",
                        modifiers, returnType, methodName, params);
            }

            // Create an instance using reflection
            Constructor<?> constructor = demoClass.getConstructor(String.class, int.class);
            Object demoInstance = constructor.newInstance("ReflectionTest", 10);

            // Find and invoke checkStatus method
            Method checkStatusMethod = demoClass.getDeclaredMethod("checkStatus");
            System.out.println("\nInvoking checkStatus():");
            Object result = checkStatusMethod.invoke(demoInstance);
            System.out.println("Result: " + result);

            // Find and invoke processWithResource method
            Method processWithResourceMethod = demoClass.getDeclaredMethod("processWithResource", Object.class);
            System.out.println("\nInvoking processWithResource():");
            processWithResourceMethod.invoke(demoInstance, "Reflection Test Data");

            // Try to access and invoke a private method (InnerHelper's formatMessage)
            try {
                Class<?> innerHelperClass = Class.forName("DecompileDemo$InnerHelper");
                Method formatMessageMethod = innerHelperClass.getDeclaredMethod("formatMessage", String.class);

                // Create an instance of InnerHelper using reflection
                Constructor<?> innerConstructor = innerHelperClass.getDeclaredConstructor(DecompileDemo.class,
                        String.class);
                innerConstructor.setAccessible(true);
                Object innerHelper = innerConstructor.newInstance(demoInstance, "REFLECTION");

                // Make the private method accessible
                formatMessageMethod.setAccessible(true);
                System.out.println("\nInvoking private formatMessage():");
                Object messageResult = formatMessageMethod.invoke(innerHelper, "Testing private method access");
                System.out.println("Result: " + messageResult);
            } catch (Exception e) {
                System.out.println("Note: Accessing inner class methods requires special handling");
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}