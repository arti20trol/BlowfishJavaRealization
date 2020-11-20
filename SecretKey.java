package blowfish.options;

public class SecretKey {
    private long[] keyArray;
    private String keyStr;
    private int sizeKey;

    public SecretKey(String key) {
        if (key.length() == 72) {
            keyStr = key;
            keyArray = stringToArray(key);
            sizeKey = key.length();
        }else if (key.length() < 72) {
            keyStr = doLoopKey(key);
            keyArray = stringToArray(keyStr);
            sizeKey = keyStr.length();
        }
        else if (key.length() > 72) {
            keyStr = key.substring(0, 72);
            keyArray = stringToArray(keyStr);
            sizeKey = keyStr.length();
        }
    }

    public String getKeyStr() {
        return keyStr;
    }

    public long[] getKeyArray() {
        return keyArray;
    }


    public long[] stringToArray(String line) {
        long[] result = new long[72];
        if (line.length() >= 72){
            for (int i = 0; i < 72; i++) {
                result[i] = line.charAt(i);
            }
        }else {
            for (int i = 0; i < line.length(); i++) {
                result[i] = line.charAt(i);
            }
        }
        return result;
    }

    public String arrayToString(long[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);
        }
        return sb.toString();
    }

    private String doLoopKey(String key) {
        StringBuilder result = new StringBuilder(key);
        int ExpectedSize = 72;
        int raznica;

        while (true) {
            raznica = ExpectedSize - result.length();
            if (result.length() < ExpectedSize) {
                result.append(key);
            }else if (result.length() >= ExpectedSize) {
                result = new StringBuilder(result.substring(0, 72));
                break;
            }
        }

        return result.toString();
    }
}
