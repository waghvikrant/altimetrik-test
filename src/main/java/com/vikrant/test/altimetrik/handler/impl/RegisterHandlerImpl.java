package com.vikrant.test.altimetrik.handler.impl;

import com.vikrant.test.altimetrik.cache.CredentialsHelper;
import com.vikrant.test.altimetrik.common.Constants;
import com.vikrant.test.altimetrik.handler.RegisterHandler;
import com.vikrant.test.altimetrik.util.PasswordUtil;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Register handler.
 */
public class RegisterHandlerImpl implements RegisterHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RegisterHandlerImpl.class);

  @Override
  public void handle(RoutingContext context) {
    JsonObject body = context.getBodyAsJson();
    if (body != null && !body.isEmpty()) {
      String username = body.getString(Constants.USERNAME);
      String password = body.getString(Constants.PASSWORD);

      if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
        try {
          System.out.println(CredentialsHelper.contains(username));
          if (!CredentialsHelper.contains(username)) {
            String cipherPassword = PasswordUtil.encrypt(password);
            CredentialsHelper.put(username, cipherPassword);
            context.response().end("User registered successfully");
          } else {
            conflict(context, "Duplicate username");
          }
        } catch (Exception e) {
          LOGGER.error(e.getMessage(), e);
          internalServerError(context);
        }
      } else {
        badRequest(context, "Username or password not provided");
      }
    } else {
      badRequest(context, "Username or password not provided");
    }
  }
}
