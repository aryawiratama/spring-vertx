package com.belajar.springvertx.verticle;

import com.belajar.springvertx.config.ApplicationConfiguration;
import com.belajar.springvertx.domain.BookCategory;
import com.belajar.springvertx.service.BookCategoryService;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.util.List;

@Component
public class BookCategoryVerticle extends AbstractVerticle {

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Autowired
    private BookCategoryService bookCategoryService;

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        router.route(HttpMethod.GET,"/book_categories").handler(this::findAllBook);
        router.post("/book_categories").handler(BodyHandler.create());
        router.post("/book_categories").handler(this::saveBookCategory);

        router.put("/book_categories/:id").handler(BodyHandler.create());
        router.put("/book_categories/:id").handler(this::updateBookCategory);

        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(applicationConfiguration.httpPort());

    }

    private void updateBookCategory(RoutingContext routingContext) {
        String id = routingContext.request().getParam("id");
        final BookCategory bookCategory = Json.decodeValue(routingContext.getBodyAsString(),BookCategory.class);
        bookCategory.setId(id);
        BookCategory responseEntity = bookCategoryService.saveBookCategory(bookCategory);
        routingContext.response()
                .setStatusCode(HttpResponseStatus.OK.code())
                .end(Json.encodePrettily(responseEntity));
    }

    private void saveBookCategory(RoutingContext routingContext) {
        final BookCategory category = Json.decodeValue(routingContext.getBodyAsString(),BookCategory.class);
        BookCategory responseEntity =  bookCategoryService.saveBookCategory(category);
        routingContext.response()
                .setStatusCode(HttpResponseStatus.CREATED.code())
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(responseEntity));
    }

    private void findAllBook(RoutingContext routingContext) {
        routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(bookCategoryService.findAllBookCategory()));
    }
}