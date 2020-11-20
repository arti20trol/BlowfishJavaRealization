package blowfish.methods;
import blowfish.options.SPbox;
import blowfish.options.SecretKey;

public class KeyExpansion {

    /**
     * @param key
     * @param pbox
     * Производит операцию xor 32-bit секретного ключа с 32-bit p-box.
     * Операция xor происходит циклически
     */
    private static void XorPbox(SecretKey key, SPbox pbox) {
        int indexOfArrayKey = 0;
        long tempKey;
        byte[] bytesKey = key.getKeyStr().getBytes();
        for (int i = 0; i < 18; i++) {
            System.out.println("OLD VALUE PS: " + pbox.getElementPs(i));
            tempKey = arrayToLong(bytesKey, indexOfArrayKey, 4);
            pbox.setElementPs(i, tempKey);
            indexOfArrayKey += 4;
            System.out.println("NEW VALUE PS: " + pbox.getElementPs(i));
        }
    }

    /**
     * @param pbox Исходный таблицы замен
     * Метод производит расширение (изменения) таблиц замен P1-P4, S1-S4
     */
    private static void expansionSPbox(SPbox pbox) {
        long a = 0L;
        long left;
        long right;
        for (int i = 0; i <= 16; i += 2) {
            a = Encrypt.functionEnc(a, pbox);
            left = SimpleMethods.trim(a >>> 32);
            right = SimpleMethods.trim(a);
            pbox.setElementPs(i, left);
            pbox.setElementPs(i + 1, right);
        }
        for (int i = 0; i <= 254; i += 2) {
            a = Encrypt.functionEnc(a, pbox);
            left = SimpleMethods.trim(a >>> 32);
            right = SimpleMethods.trim(a);
            pbox.setElementKs0(i, left);
            pbox.setElementKs0(i + 1, right);
        }
        for (int i = 0; i <= 254; i += 2) {
            a = Encrypt.functionEnc(a, pbox);
            left = SimpleMethods.trim(a >>> 32);
            right = SimpleMethods.trim(a);
            pbox.setElementKs1(i, left);
            pbox.setElementKs1(i + 1, right);
        }
        for (int i = 0; i <= 254; i += 2) {
            a = Encrypt.functionEnc(a, pbox);
            left = SimpleMethods.trim(a >>> 32);
            right = SimpleMethods.trim(a);
            pbox.setElementKs2(i, left);
            pbox.setElementKs2(i + 1, right);
        }
        for (int i = 0; i <= 254; i += 2) {
            a = Encrypt.functionEnc(a, pbox);
            left = SimpleMethods.trim(a >>> 32);
            right = SimpleMethods.trim(a);
            pbox.setElementKs3(i, left);
            pbox.setElementKs3(i + 1, right);
        }
    }

    /**
     *
     * @param data Байтовый массив
     * @param begin Позиция в массиве с которой нужно прочитать числа
     * @param value Сдвиг (количество читаемых элементов)
     * @return  Число типа Long состоящие из элементов массива
     */
    private static long arrayToLong(byte[] data, int begin, int value) {
        StringBuilder sb = new StringBuilder();
        for (int i = begin; i < begin + value; i++) {
            sb.append(data[i]);
        }
        return Long.parseLong(sb.toString());
    }

    public static void keyExpansion(SecretKey key, SPbox sPbox) {
        XorPbox(key, sPbox);
        expansionSPbox(sPbox);
    }
}
