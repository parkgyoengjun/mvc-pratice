package org.example.mvc;

import org.example.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationHandlerAdapter implements HandlerAdapter {


    @Override
    public boolean supports(Object handler) {
        return handler instanceof AnnotationHandler;
        // AnnotationHandler 여야지만 이 adapter 를 사용가능하다.
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String viewName = ((AnnotationHandler) handler).handle(request,response);
        return new ModelAndView(viewName);
    }
}
