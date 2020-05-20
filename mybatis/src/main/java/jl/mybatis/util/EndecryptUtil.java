package jl.mybatis.util;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * XiaodaiAESEncryptor
 *
 * @author Liu Chang
 * @date 2019/12/25
 */
@Getter
@Setter
public class EndecryptUtil {

    public static final String AES_KEY = "zxAMU%AutPr6}LasE0#drPaiI3mD?3HuZq@3";

    private static final String EMPTY_STRING = "";

    public String getType() {
        return "XiaodaiAES";
    }

    public void init() {
    }

    @SneakyThrows
    public static String encrypt(final Object plaintext) {
        if (plaintext == null || EMPTY_STRING.equals(plaintext)) {
            return null;
        }
        byte[] result = getCipher(Cipher.ENCRYPT_MODE).doFinal(String.valueOf(plaintext).getBytes(UTF_8));
        return BaseEncoding.base64().encode(result);
    }

    @SneakyThrows
    public static String decrypt(final String ciphertext) {
        if (null == ciphertext) {
            return null;
        }
        byte[] result = getCipher(Cipher.DECRYPT_MODE).doFinal(BaseEncoding.base64().decode(ciphertext));
        return new String(result);
    }

    private static Cipher getCipher(final int decryptMode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher result = Cipher.getInstance("AES");
        result.init(decryptMode, new SecretKeySpec(createSecretKey(), "AES"));
        return result;
    }

    private static byte[] createSecretKey() {
        final byte[] finalKey = new byte[16];
        int i = 0;
        for (byte b : AES_KEY.getBytes(Charsets.UTF_8)) {
            finalKey[i++ % 16] ^= b;
        }
        return finalKey;
    }


}
