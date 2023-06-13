package jl.cipher.aes;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AesTest
 *
 * @author Liu Chang
 * @date 2019/11/28
 */
public class AesTest {

    public static void main(String[] args) {
        String key =  Hashing.md5().hashString("zxAMU%AutPr6}LasE0#drPaiI3mD?3HuZq@3", Charsets.UTF_8).toString();
        final int length = encryptAes(key, "6214830157205609").length();
        System.out.println(length);
    }

    /**
     * AES加密
     *
     * @param aesKey 密钥
     * @param plainText 明文
     * @return 密文
     */
    public static String encryptAes(String aesKey, String plainText) {
        try {
            byte[] key = aesKey.getBytes(Charsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptBytes = cipher.doFinal(plainText.getBytes(Charsets.UTF_8));
            // Base64一下，解密的时候先解码
            return BaseEncoding.base64().encode(encryptBytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES解密
     *
     * @param aesKey 密钥
     * @param cipherText 密文
     * @return 明文
     */
    public static String decryptAes(String aesKey, String cipherText) {
        try {
            byte[] key = aesKey.getBytes(Charsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptBytes = cipher.doFinal(BaseEncoding.base64().decode(cipherText));
            return new String(decryptBytes, Charsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
