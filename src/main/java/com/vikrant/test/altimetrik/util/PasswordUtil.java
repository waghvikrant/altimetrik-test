package com.vikrant.test.altimetrik.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(PasswordUtil.class);

  private static final String passkey = "SECRET-PASSPHRASE";
  private static StandardPBEStringEncryptor encryptor;

  static {
    LOGGER.info("Initializing password encryptor");
    encryptor = new StandardPBEStringEncryptor();
    encryptor.setPassword(passkey);
  }

  public static String encrypt(String plaintext) {
    return encryptor.encrypt(plaintext);
  }

  public static String decrypt(String cipherText) {
    return encryptor.decrypt(cipherText);
  }
}
