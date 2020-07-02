package com.vikrant.test.altimetrik.handler.impl;

import com.vikrant.test.altimetrik.handler.ServiceHandler;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Service handler.
 */
public class ServiceHandlerImpl implements ServiceHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceHandlerImpl.class);

  private static Buffer file;

  @Override
  public void handle(RoutingContext context) {
    LOGGER.debug("Authenticating request");
    if (file == null || file.length() == 0) {
      file = context.vertx().fileSystem().readFileBlocking("response.json");
    }
    context.response().end(file);
  }
}
