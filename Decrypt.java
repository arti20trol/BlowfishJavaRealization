package blowfish.methods;
import blowfish.options.SPbox;

public class Decrypt {
    public static long functionDec(final long data_64, SPbox sp) {
        long block = data_64;
        long left = SimpleMethods.trim(block >>> 32);
        long right = SimpleMethods.trim(block);

        long temp;
        for (int i = 17; i > 1; i--) {
            left = left ^ sp.getElementPs(i);
            right = FunctionF.F(left, sp) ^ right;

            temp = left;
            left = right;
            right = temp;
        }
        temp = left;
        left = right;
        right = temp;
        right = right ^ sp.getElementPs(1);
        left = left ^ sp.getElementPs(0);
        return (left << 32) | right;
    }
}
