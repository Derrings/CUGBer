package edu.cugb.jts.cugber.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * This utils can used in any situation you want to make an object
 * be encrypted.
 * When getting an object AESUtil will firstly convert it to an JSON
 * string, so that we can use Base64 to encode it. After getting the
 * Base64-encoded string S, we create an random key K by user's secret.
 * Then use the K to encrypt S with AES.
 * We always get the same key, as long as user provides the same secret.
 *
 * @author Derrings
 */
@Slf4j
@Component
public class AESUtil {
    private static final int RANDOM_SOURCE_BITS = 128;
    private static final String ENCRYPT_ALGORITHM = "AES";
    @Resource
    private ObjectMapper mapper;

    // Generate a secret key depends on the encode rule.
    private SecretKey generateSecretKey(String encodeRules) throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(ENCRYPT_ALGORITHM);
        keygen.init(RANDOM_SOURCE_BITS, new SecureRandom(encodeRules.getBytes()));
        SecretKey originalKey = keygen.generateKey();
        byte[] raw = originalKey.getEncoded();
        return new SecretKeySpec(raw, ENCRYPT_ALGORITHM);
    }

    // Creating an random string, which can be used as a encode rule in AES encoder
    public String randomSecretGenerator() {
        int secretLength = (int) (Math.random() * 20);
        StringBuilder secretBuilder = new StringBuilder();
        while(secretLength-- != 0) {
            int ASCIIBit = (int) ((126 - 33) * Math.random() + 33);
            secretBuilder.append((char) ASCIIBit);
        }
        String secret = secretBuilder.toString();
        log.debug("Random secret has successfully create:" + secret);
        return secret;
    }

    /**
     * Convert an object into a string of ciphertext encrypted by AES.
     * @param encodeRules A string used to generate secret key. Always a random string.
     * @param obj The target object which needs to be converted to a string of ciphertext.
      */
    public String AESEncode(String encodeRules, Object obj) {
        try {
            String json = mapper.writeValueAsString(obj);
            SecretKey key = generateSecretKey(encodeRules);
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] byteEncode = json.getBytes(StandardCharsets.UTF_8);
            byte[] byteAES = cipher.doFinal(byteEncode);
            String result = new BASE64Encoder().encode(byteAES);
            log.debug("The AES encoder return:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Decode the ciphertext into a Java object.
     * @param encodeRules Must be the same as encode encode rules.
     * @param json Json string.
     * @param clazz The original object class.
     */
    public<T> T AESDecode(String encodeRules, String json, Class<T> clazz) {
        T obj = null;
        try {
            SecretKey key = generateSecretKey(encodeRules);
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] byteContent = new BASE64Decoder().decodeBuffer(json);
            byte[] byteDecode = cipher.doFinal(byteContent);
            String AESDecode = new String(byteDecode, StandardCharsets.UTF_8);
            obj = mapper.readValue(AESDecode, clazz);
            log.debug("The AES decoder return:" + obj);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
