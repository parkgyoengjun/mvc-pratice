package org.example.mvc;

import org.example.mvc.contoller.HandlerKey;
import org.example.mvc.contoller.RequestMethod;
import org.example.mvc.view.JspViewResolver;
import org.example.mvc.view.ModelAndView;
import org.example.mvc.view.View;
import org.example.mvc.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/") // 어떠한 경로로 와도 dispatcherServlet 으로 오게끔
public class DispatcherServlet extends HttpServlet { // 모든 요청메세지를 받고 뿌려주는 역할


    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private List<HandlerAdapter> handlerAdapters;

    private List<ViewResolver> viewResolvers;
    /*
        List<>
        1. 순서가 정해지고 중복허용
        2. 인덱스로 관리하기 때문에 인덱스로 접근이 가능하다.
        3. 크기가 가변적이다.

        private ViewResolver viewResolvers;
    */

    private List<HandlerMapping> handlerMappings;
    // 인터페이스 타입을 선언( 구체적인 클래스 타입으로 선언하지 않겠다 )
//    private RequestMappingHandlerMapping rmhm;

    @Override
    public void init() throws ServletException {
        RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
        rmhm.init();

        AnnotationHandlerMapping ahm = new AnnotationHandlerMapping("org.example");
        ahm.initialize();

        handlerMappings = List.of(rmhm, ahm); // 애노테이션으로도 하나 더 추가
        viewResolvers = Collections.singletonList(new JspViewResolver());
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started. ");
        String requestURI = request.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

        try {
//            Controller handler = rmhm.findHandler(request.getRequestURI());
            // /user
//            Controller handler = rmhm.findHandler(new HandlerKey(RequsetMethod.valueOf(request.getMethod()), request.getRequestURI()));
            // new HandlerKey(RequsetMethod.valueOf(request.getMethod()) 는 요청받은게 GET 인지 POST 인지 구별해주는 코드
//            String viewName = handler.handleRequest(request, response);
            // /user, new UserListController

/*
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName); // handler 가 viewRe
            // 그냥 넘기면 정보들이 사라지니까 requestDispathcer 에 저장해서 forward 로 넘겨줌
            requestDispatcher.forward(request, response);
             -> JspView 로 옮김
*/
            /*
                서블릿에서 값을 넘겨주고 해당 페이지에서 처리할 수 있도록 하는 방법
                RequestDispatcher view = request.getRequestDispatcher("경로");
                request.setAttribute("key",value);
                view.forward(request, response);
             */

            // viewName 이 redirect:/users 로 감 ( redirect vs forward )

/*
                for (ViewResolver viewResolver : viewResolvers) { // redirect 와 forward 구분 ( 향상된 for문 - 대상은 배열 or 여러 원소를 포함한 자료형이어야 한다.)
                View view = viewResolver.resolveView(viewName);
                view.render(new HashMap<>(), request, response); // 선택된 view에 따라 적절한 view 를 선택함
                }
            */

            Object handler = handlerMappings.stream()
                    .filter(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
                    .map(hm -> hm.findHandler(new HandlerKey(requestMethod, requestURI)))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("no handler for "));
            // findHandler 가 여러개니까 stream 을 해준다고 한다.

            HandlerAdapter handlerAdapter = handlerAdapters.stream()
                    .filter(ha -> ha.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));
            /*
                findFirst()는 filter 조건에 일치하는 element 1개를 Optional로 리턴합니다. 조건에 일치하는 요소가 없다면 empty가 리턴됩니다.
                orElseThrow 는 if문과 비슷하다. 예외를 처리하기 위해 ~~
             */

            ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), request, response);
            }


        } catch (Exception e) {
            log.error("exception occurredL [{}]", e.getMessage(), e);
            throw new ServletException(e);

        }
    }
}
