public class ArithmeticOperations {
    // Native methods for arithmetic operations
    public native int add(int a, int b);
    public native int subtract(int a, int b);
    public native int multiply(int a, int b);
    public native int divide(int a, int b);

    // Load the native library
    static {
        System.loadLibrary("ArithmeticOperations");
    }

    // Main method to test the arithmetic operations
    public static void main(String[] args) {
        ArithmeticOperations ops = new ArithmeticOperations();
        
        int a = 10;
        int b = 5;

        // Perform and display the results of the operations
        System.out.println("Addition: " + ops.add(a, b));
        System.out.println("Subtraction: " + ops.subtract(a, b));
        System.out.println("Multiplication: " + ops.multiply(a, b));
        System.out.println("Division: " + ops.divide(a, b));
    }
}
