package com.vikrant.test.altimetrik;

import com.vikrant.test.altimetrik.handler.AuthenticationHandler;
import com.vikrant.test.altimetrik.handler.LoginHandler;
import com.vikrant.test.altimetrik.handler.RegisterHandler;
import com.vikrant.test.altimetrik.handler.ServiceHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Server verticle.
 */
public class ServerVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServerVerticle.class);

  @Override
  public void start(Promise<Void> promise) {
    LOGGER.info("Booting up server");
    try {
      Router router = Router.router(vertx);
      router.route().handler(BodyHandler.create());
      router.route("/register").handler(RegisterHandler.create()).method(HttpMethod.POST);
      router.route("/login").handler(LoginHandler.create()).method(HttpMethod.POST);
      router.route().handler(AuthenticationHandler.create());
      router.route().handler(ServiceHandler.create());
      LOGGER.info("Router configured");

      vertx.createHttpServer().requestHandler(router).listen(8080, handler -> {
        if (handler.succeeded()) {
          LOGGER.info("Server Started");
          promise.complete();
        } else {
          LOGGER.info("Server startup failed");
          promise.fail("Server startup failed");
        }
      });
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
  }
}
