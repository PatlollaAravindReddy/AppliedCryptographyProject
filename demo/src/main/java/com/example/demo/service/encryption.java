package com.example.demo.service;
import javax.crypto.Cipher;
import java.security.Key;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import com.example.demo.constants.constant;
import com.example.demo.dto.Patient;
import com.example.demo.dto.userDetails;
import org.springframework.stereotype.Component;

@Component
public class encryption {

    public String aa = "";

    public Patient aesEncryption(Patient data) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance(constant.ALGORITHM);
        keyGenerator.init(constant.aesBytes);
        Key key = keyGenerator.generateKey();

        byte[] encodedKey = key.getEncoded();
        String aesKeyGenerator = Base64.getEncoder().encodeToString(encodedKey);

        Cipher cipher = Cipher.getInstance(constant.ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(aesKeyGenerator.getBytes(), constant.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        data.setName(Base64.getEncoder().encodeToString(cipher.doFinal(data.getName().getBytes())));
        data.setPhoneNumber(Base64.getEncoder().encodeToString(cipher.doFinal(data.getPhoneNumber().getBytes())));
        data.setEmail(Base64.getEncoder().encodeToString(cipher.doFinal(data.getEmail().getBytes())));
        data.setAddress(Base64.getEncoder().encodeToString(cipher.doFinal(data.getAddress().getBytes())));
        data.setKey(aesKeyGenerator);
        return data;
    }

    public String decryption(String key) throws Exception{
        Cipher cipher = Cipher.getInstance(constant.ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), constant.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(key));
        return new String(decryptedBytes);
    }
}
