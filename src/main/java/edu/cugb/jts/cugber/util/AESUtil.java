package edu.cugb.jts.cugber.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.base64.Base64Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Slf4j
@Component
public class AESUtil {
    private static final int RANDOM_SOURCE_BITS = 128;
    private static final String ENCRYPT_ALGORITHM = "AES";
    @Resource
    private ObjectMapper mapper;

    private SecretKey generateSecretKey(String encodeRules) throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(ENCRYPT_ALGORITHM);
        keygen.init(RANDOM_SOURCE_BITS, new SecureRandom(encodeRules.getBytes()));
        SecretKey originalKey = keygen.generateKey();
        byte[] raw = originalKey.getEncoded();
        return new SecretKeySpec(raw, ENCRYPT_ALGORITHM);
    }
    // Using AES encrypt an object to ciphertext.
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
