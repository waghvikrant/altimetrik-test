package com.vikrant.test.altimetrik.handler.impl;

import com.vikrant.test.altimetrik.cache.CredentialsHelper;
import com.vikrant.test.altimetrik.common.Constants;
import com.vikrant.test.altimetrik.handler.LoginHandler;
import com.vikrant.test.altimetrik.jwt.TokenHelper;
import com.vikrant.test.altimetrik.util.PasswordUtil;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Login handler.
 */
public class LoginHandlerImpl implements LoginHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoginHandlerImpl.class);

  @Override
  public void handle(RoutingContext context) {
    LOGGER.debug("Executing LoginHandler");
    JsonObject body = context.getBodyAsJson();
    if (body != null && !body.isEmpty()) {
      String username = body.getString(Constants.USERNAME);
      String password = body.getString(Constants.PASSWORD);
      if (body.containsKey(Constants.USERNAME) && body.containsKey(Constants.PASSWORD)) {
        try {
          if (CredentialsHelper.contains(username)) {
            String decryptedPassword = PasswordUtil.decrypt(CredentialsHelper.get(username));
            if (decryptedPassword.equals(password)) {
              LOGGER.debug("Login successful, generating token");
              String token = TokenHelper.getInstance(context.vertx()).generateToken(username);
              context.response().putHeader("Authorization", token)
                  .end(token);
            } else {
              unauthorized(context, "Invalid password");
            }
          } else {
            notFound(context, "User not registered");
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
