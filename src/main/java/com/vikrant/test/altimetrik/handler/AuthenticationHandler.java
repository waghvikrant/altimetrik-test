package com.vikrant.test.altimetrik.handler;

import com.vikrant.test.altimetrik.handler.impl.AuthenticationHandlerImpl;

/**
 * The interface Authentication handler takes care of the request authentication
 */
public interface AuthenticationHandler extends ResponseHandler {

  /**
   * Create authentication handler.
   *
   * @return the authentication handler
   */
  static AuthenticationHandler create() {
    return new AuthenticationHandlerImpl();
  }
}
