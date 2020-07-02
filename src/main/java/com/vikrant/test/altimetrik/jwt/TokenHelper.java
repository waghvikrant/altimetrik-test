package com.vikrant.test.altimetrik.jwt;

import com.vikrant.test.altimetrik.common.Constants;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.jwt.JWTOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Token helper.
 */
public class TokenHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(TokenHelper.class);

  private static final String uuid = "NFHTH3759FHDNFJ57DGNTK570T5JBNCHDG2750OFGFJFHDGW";
  private static JWTAuth provider;
  private static TokenHelper tokenHelper;

  private TokenHelper(Vertx vertx) {
    if (provider == null) {
      LOGGER.info("Creating new TokenHelper");
      provider = JWTAuth.create(vertx, new JWTAuthOptions()
          .addPubSecKey(new PubSecKeyOptions()
              .setAlgorithm("HS256")
              .setPublicKey(uuid)
              .setSymmetric(true)));
    }
  }

  /**
   * Gets instance of TokenHelper.
   *
   * @param vertx the vertx
   * @return the instance
   */
  public static TokenHelper getInstance(Vertx vertx) {
    if (tokenHelper == null) {
      tokenHelper = new TokenHelper(vertx);
    }
    return tokenHelper;
  }

  /**
   * Generate token string.
   *
   * @param username the username
   * @return the string
   */
  public String generateToken(String username) {
    LOGGER.debug("Generating token for user {}", username);
    return provider.generateToken(
        new JsonObject().put("username", username), new JWTOptions().setExpiresInMinutes(5));
  }

  /**
   * Authenticate the token.
   *
   * @param token         the token
   * @param resultHandler the result handler
   */
  public void authenticate(String token, Handler<AsyncResult<String>> resultHandler) {
    LOGGER.debug("Authenticating token");
    provider.authenticate(new JsonObject().put("jwt", token), handler -> {
      if (handler.succeeded()) {
        LOGGER.debug("Token authentication successful");
        resultHandler.handle(
            Future.succeededFuture(handler.result().principal().getString(Constants.USERNAME)));
      } else {
        LOGGER.error(handler.cause().getMessage(), handler.cause());
        resultHandler.handle(Future.failedFuture(handler.cause()));
      }
    });
  }

}
