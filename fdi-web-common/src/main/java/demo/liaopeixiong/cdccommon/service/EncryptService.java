package demo.liaopeixiong.cdccommon.service;

import demo.liaopeixiong.cdccommon.exception.CdcRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EncryptService {

    @Value("${encrypt.file.path}")
    private String encryptFilePath;

    private final static AtomicReference<SecretKey> pbkdf2Key = new AtomicReference<>();
    private final static AtomicReference<SecretKey> aesKey = new AtomicReference<>();
    private final static AtomicBoolean initialized = new AtomicBoolean(false);

    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    public String encrypt(String text) {
        if (!this.initialized.get()) {
            init();
        }
        try {
            SecretKey pbkdf2Key = this.pbkdf2Key.get();
            SecretKey aesKey = this.aesKey.get();
            byte[] aesEncrypted = encryptWithAES(text, aesKey);
            byte[] pbkdf2Encrypted = encryptWithAES(encoder.encodeToString(aesEncrypted), pbkdf2Key);
            return encoder.encodeToString(pbkdf2Encrypted);
        } catch (Exception exception) {
            throw new CdcRuntimeException(exception);
        }
    }

    public String decrypt(String text) {
        if (!this.initialized.get()) {
            init();
        }
        try {
            SecretKey pbkdf2Key = this.pbkdf2Key.get();
            SecretKey aesKey = this.aesKey.get();
            String pbkdf2Decrypted = decryptWithAES(decoder.decode(text), pbkdf2Key);
            return decryptWithAES(decoder.decode(pbkdf2Decrypted), aesKey);
        } catch (Exception exception) {
            throw new CdcRuntimeException(exception);
        }
    }

    private synchronized void init() {
        if (!this.initialized.get()) {
            Path aesKeyFilePath = Paths.get(encryptFilePath + "/aeskey");
            Path saltFilePath = Paths.get(encryptFilePath + "/salt");
            Path passwordFilePath = Paths.get(encryptFilePath + "/password");
            try {
                String aesKeyStr = new String(Files.readAllBytes(aesKeyFilePath), StandardCharsets.UTF_8);
                String saltStr = new String(Files.readAllBytes(saltFilePath), StandardCharsets.UTF_8);
                String passwordStr = new String(Files.readAllBytes(passwordFilePath), StandardCharsets.UTF_8);
                if (StringUtils.isBlank(aesKeyStr) || StringUtils.isBlank(saltStr) || StringUtils.isBlank(passwordStr)) {
                    throw new CdcRuntimeException("File encryption and decryption errors!");
                }
                byte[] salt = decoder.decode(saltStr);
                this.pbkdf2Key.set(generatePBKDF2Key(passwordStr, salt, 65536, 256));
                this.aesKey.set(new SecretKeySpec(decoder.decode(aesKeyStr), "AES"));
            } catch (Exception exception) {
                throw new CdcRuntimeException(exception);
            } finally {
                this.initialized.set(true);
            }
        }
    }

    // AES加密（使用CBC模式更安全）
    private static byte[] encryptWithAES(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecureRandom random = new SecureRandom();
        byte[] ivBytes = new byte[16];
        random.nextBytes(ivBytes);
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);

        byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(ivBytes);
        outputStream.write(encrypted);
        return outputStream.toByteArray();
    }

    // AES解密
    private static String decryptWithAES(byte[] encryptedData, SecretKey key) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(encryptedData);
        byte[] ivBytes = new byte[16];
        inputStream.read(ivBytes);
        byte[] encrypted = new byte[encryptedData.length - 16];
        inputStream.read(encrypted);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
    }

    // PBKDF2密钥生成
    public static SecretKey generatePBKDF2Key(String password, byte[] salt,
                                              int iterations, int keyLength) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
}
