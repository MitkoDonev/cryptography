package project;


public class Crypter implements Crypto {

    public byte[] encryptAlgo(byte[] data, int num) {
        byte[] enc = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            if (i % 2 == 0) {
                enc[i] = (byte) (data[i] + num);
            } else {
                enc[i] = (byte) (data[i] - num);
            }
        }
        return enc;
    }

    public byte[] decryptAlgo(byte[] data, int num) {
        byte[] enc = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            if (i % 2 == 0) {
                enc[i] = (byte) (data[i] - num);
            } else {
                enc[i] = (byte) (data[i] + num);
            }
        }
        return enc;
    }

    @Override
    public byte[] encryptSHA256(byte[] data) {
        int num = 1;
        return encryptAlgo(data, num);
    }

    @Override
    public byte[] encryptCBC654(byte[] data) {
        int num = 5;
        return encryptAlgo(data, num);
    }

    @Override
    public byte[] encryptCFB983(byte[] data) {
        int num = 10;
        return encryptAlgo(data, num);
    }

    @Override
    public byte[] decryptSHA256(byte[] data) {
        int num = 1;
        return decryptAlgo(data, num);
    }

    @Override
    public byte[] decryptCBC654(byte[] data) {
        int num = 5;
        return decryptAlgo(data, num);
    }

    @Override
    public byte[] decryptCFB983(byte[] data) {
        int num = 10;
        return decryptAlgo(data, num);
    }
}
