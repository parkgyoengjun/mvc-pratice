package org.example.mvc;

import org.example.mvc.contoller.*;

import java.util.HashMap;
import java.util.Map;


public class RequestMappingHandlerMapping implements HandlerMapping {

    //    private Map<String, Controller> mappings = new HashMap<>();
    private Map<HandlerKey, Controller> mappings = new HashMap<>();


    void init() { // 경로를 추가해줄때마다 DispacherServlet 에서 적절한 Controller를 찾아서 화면에 노출
/*
        mappings.put("/", new HomeController());
        mappings.put("/users", new UserListController()); // get
        mappings.put("/users", new UserCreateController()); // post  --> 구분이 안된다. 밑에 처럼 해줘야함
*/

//        mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController()); 애노테이션형태으로 변경
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController()); // 위에는 처리를 하고 이동
//        mappings.put(new HandlerKey(RequsetMethod.GET, "/user/form"), new ForwardController("/user/form.jsp")); // 바로 이동
        mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form")); // 바로 이동
    }


//    public Controller findHandler(String uriPath) {return mappings.get(uriPath); }

    // HandlerKey 와 uriPath 는 객체라서 객체끼리의 비교라서 equalshashcode 를 HandlerKey클래스에 만들어줘야함
    public Controller findHandler(HandlerKey handlerKey) {
        return mappings.get(handlerKey);
    }

}
