package ru.gretchen.http.app;

import lombok.extern.java.Log;
import ru.gretchen.http.framework.Server;
import ru.gretchen.http.framework.resolver.argument.RequestHandlerMethodArgumentResolver;
import ru.gretchen.http.framework.resolver.argument.RequestHeaderHandlerMethodArgumentResolver;
import ru.gretchen.http.framework.resolver.argument.ResponseHandlerMethodArgumentResolver;

@Log
public class Main {
  public static void main(String[] args) {
    final var server = new Server();
    server.autoRegisterHandlers("org.example.http.app");
    server.addArgumentResolver(
        new RequestHandlerMethodArgumentResolver(),
        new ResponseHandlerMethodArgumentResolver(),
        new RequestHeaderHandlerMethodArgumentResolver()
    );
    new Thread(() -> {
      try {
        Thread.sleep(1000);
        server.stop();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    server.listen(9999);
  }
}
