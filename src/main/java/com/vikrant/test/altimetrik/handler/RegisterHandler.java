package com.vikrant.test.altimetrik.handler;

import com.vikrant.test.altimetrik.handler.impl.RegisterHandlerImpl;

/**
 * The interface Register handler handles the register requests
 */
public interface RegisterHandler extends ResponseHandler {

  /**
   * Create register handler.
   *
   * @return the register handler
   */
  static RegisterHandler create() {
    return new RegisterHandlerImpl();
  }
}
