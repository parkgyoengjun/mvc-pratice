package org.example.mvc;

import org.example.mvc.contoller.HandlerKey;

public interface HandlerMapping {// 클라이언트가 적은 주소가 post,get 인지에따라 Controller 를 지정해줌
    Object findHandler(HandlerKey handlerKey);
}
