package blowfish.methods;
import blowfish.options.SPbox;

public class Encrypt {

    /**
     * Шифрование 64-битного блока ОТ
     * Сеть Фейстеля
     * @param data_64  64-bit блок ОТ
     * @param sp       Таблицы замен (предварительно расширенные)
     * @return         блок зашифрованного текста
     */
    public static long functionEnc(long data_64, SPbox sp) {
        long left = SimpleMethods.trim(data_64 >>> 32);
        long right = SimpleMethods.trim(data_64);

        long temp;
        for (int i = 0; i < 16; i++) {
            left = left ^ sp.getElementPs(i);
            right = FunctionF.F(left, sp) ^ right;

            temp = left;
            left = right;
            right = temp;
        }
        temp = left;
        left = right;
        right = temp;

        right = right ^ sp.getElementPs(16);
        left = left ^ sp.getElementPs(17);
        return (left << 32) | right;
    }


}
