package com.vikrant.test.altimetrik.handler;

import com.vikrant.test.altimetrik.handler.impl.ServiceHandlerImpl;

/**
 * The interface Service handler handles the service requests.
 */
public interface ServiceHandler extends ResponseHandler {

  /**
   * Create service handler.
   *
   * @return the service handler
   */
  static ServiceHandler create() {
    return new ServiceHandlerImpl();
  }
}
