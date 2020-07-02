package com.vikrant.test.altimetrik.handler.impl;

import com.vikrant.test.altimetrik.handler.AuthenticationHandler;
import com.vikrant.test.altimetrik.jwt.TokenHelper;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Authentication handler Impl.
 */
public class AuthenticationHandlerImpl implements AuthenticationHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationHandlerImpl.class);

  @Override
  public void handle(RoutingContext context) {
    LOGGER.debug("Authenticating request");

    String token = context.request().getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
    LOGGER.debug("token: {}", token);

    TokenHelper.getInstance(context.vertx()).authenticate(token, handler -> {
      if (handler.succeeded()) {
        String renewedToken = TokenHelper.getInstance(context.vertx())
            .generateToken(handler.result());
        context.response().putHeader("Authorization", renewedToken);
        context.next();
      } else {
        unauthorized(context, "Token expired");
      }
    });

  }
}
