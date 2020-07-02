package com.vikrant.test.altimetrik.cache;

import com.vikrant.test.altimetrik.util.VertxUtil;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Credentials helper, provides a realtime cache that stores the credentials.
 */
public class CredentialsHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(CredentialsHelper.class);
  private static final String CREDENTIALS_FILE = "credentials.json";
  private static JsonObject cache;
  private static Vertx vertx = VertxUtil.getDefaultVertxInstance();

  /*
   * Populate the cache with existing credentials, if the existing credentials are not present then create the new one
   */
  static {
    LOGGER.info("Populating credentials");
    try {
      if (vertx.fileSystem().existsBlocking(CREDENTIALS_FILE)) {
        LOGGER.debug("Using existing {}", CREDENTIALS_FILE);

        cache = vertx.fileSystem().readFileBlocking(CREDENTIALS_FILE)
            .toJsonObject();
      } else {
        LOGGER.debug("Creating new {}", CREDENTIALS_FILE);

        cache = new JsonObject();
        vertx.fileSystem().createFileBlocking(CREDENTIALS_FILE)
            .writeFileBlocking(CREDENTIALS_FILE, cache.toBuffer());
      }
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  /**
   * Get the value from the cache as per the key.
   *
   * @param key the key
   * @return the string
   */
  public static String get(String key) {
    return cache.getString(key);
  }

  /**
   * Put the key value pair in the cache.
   *
   * @param key   the key
   * @param value the value
   */
  synchronized public static void put(String key, String value) {
    cache.put(key, value);
    vertx.fileSystem()
        .writeFileBlocking(CREDENTIALS_FILE, cache.toBuffer());
  }

  /**
   * Check if the cache contains the key.
   *
   * @param key the key
   * @return the boolean
   */
  public static boolean contains(String key) {
    return cache.containsKey(key);
  }
}
