package blowfish.methods;
import blowfish.options.SPbox;

public class FunctionF {
    public static long F(long data, SPbox sp) {
        long result = 0;
        long copyData = data;

        long x4 = copyData & 0xFF;
        copyData = copyData >> 8;

        long x3 = copyData & 0xFF;
        copyData = copyData >> 8;

        long x2 = copyData & 0xFF;
        copyData = copyData >> 8;

        long x1 = copyData & 0xFF;

        result = sp.getElementKs0((int)x1) + sp.getElementKs1((int)x2);
        result = result ^ sp.getElementKs2((int)x3);
        result = result + sp.getElementks3((int)x4);
        return result;
    }
}
