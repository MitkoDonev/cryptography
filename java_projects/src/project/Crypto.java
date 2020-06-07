package project;

public interface Crypto {
    byte[] encryptSHA256(byte[] data);
    byte[] encryptCBC654(byte[] data);
    byte[] encryptCFB983(byte[] data);

    byte[] decryptSHA256(byte[] data);
    byte[] decryptCBC654(byte[] data);
    byte[] decryptCFB983(byte[] data);

}
