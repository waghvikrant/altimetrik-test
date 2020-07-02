package com.vikrant.test.altimetrik.handler;

import com.vikrant.test.altimetrik.handler.impl.LoginHandlerImpl;

/**
 * The interface Login Handler handles the login requests.
 */
public interface LoginHandler extends ResponseHandler {

  /**
   * Create login handler.
   *
   * @return the login handler
   */
  static LoginHandler create() {
    return new LoginHandlerImpl();
  }
}
