package com.vikrant.test.altimetrik.handler;


import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * The interface Response handler handles all responses that can be sent back to the client
 */
public interface ResponseHandler extends Handler<RoutingContext> {

  /**
   * Internal server error.
   *
   * @param context the context
   */
  default void internalServerError(RoutingContext context) {
    context.response().setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code()).end();
  }

  /**
   * Conflict.
   *
   * @param context the context
   * @param message the message
   */
  default void conflict(RoutingContext context, String message) {
    context.response().setStatusCode(HttpResponseStatus.CONFLICT.code()).end(message);
  }

  /**
   * Not found.
   *
   * @param context the context
   * @param message the message
   */
  default void notFound(RoutingContext context, String message) {
    context.response().setStatusCode(HttpResponseStatus.NOT_FOUND.code()).end(message);
  }

  /**
   * Bad request.
   *
   * @param context the context
   * @param message the message
   */
  default void badRequest(RoutingContext context, String message) {
    context.response().setStatusCode(HttpResponseStatus.BAD_REQUEST.code()).end(message);
  }

  /**
   * Unauthorized.
   *
   * @param context the context
   * @param message the message
   */
  default void unauthorized(RoutingContext context, String message) {
    context.response().setStatusCode(HttpResponseStatus.UNAUTHORIZED.code()).end(message);
  }


}
