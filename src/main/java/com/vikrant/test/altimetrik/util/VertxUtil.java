package com.vikrant.test.altimetrik.util;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertxUtil {

  // Instance of Vertx
  private static Vertx vertx;

  // Utility classes, which are collections of static members, are not meant to
  // be instantiated. Hence hiding the constructor
  private VertxUtil() {
    // Throw exception if constructor is invoked
    throw new IllegalStateException("Utility class");
  }

  /* Public Methods */

  /**
   * This method creates an instance of {@link Vertx} with default {@link VertxOptions} as Singleton
   * Pattern with Lazy Initialization.
   *
   * @return the instance of {@link Vertx}
   */
  public static Vertx getDefaultVertxInstance() {
    if (null == vertx) {
      // Thread Safe.
      synchronized (VertxUtil.class) {
        if (null == vertx) {
          // Create VertxOptions with default configuration
          VertxOptions vertxOptions = new VertxOptions();
          // Create instance of Vertx with default VertxOptions
          vertx = Vertx.vertx(vertxOptions);
        }
      }
    }
    return vertx;
  }

}
