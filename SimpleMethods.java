package blowfish.methods;

public class SimpleMethods {
    public static long trim(long data) {
        return (data & 0xFFFFFFFFL);
    }
}
