package org.example.mvc;

import org.example.mvc.annotaion.Controller;
import org.example.mvc.annotaion.RequestMapping;
import org.example.mvc.contoller.HandlerKey;
import org.example.mvc.contoller.RequestMethod;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping{


    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler > handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        /*
            메서드의 매개변수 개수를 동적으로 지정해주는 방법이다.
            타입… 변수명’ 형식으로 선언
         */
        this.basePackage = basePackage;
    }

    public void initialize() {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> clazzWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);
        /*
            basePackage 밑에 controller annotation 이 붙어져잇는 클래스 다 가져옴 (homecontroller )
         */

        clazzWithControllerAnnotation.forEach(clazz -> // class 정보
                Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethod -> { // method 정보
                    RequestMapping requestMapping = declaredMethod.getDeclaredAnnotation(RequestMapping.class);

                    //     @RequestMapping(value = "/", method = RequestMethod.GET)
                    Arrays.stream(getRequestMethod(requestMapping))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz,declaredMethod)
                            ));
                })
        );
        /*
           annotation Controller 가 붙여져 있는 클래스 중에 RequestMapping(annotation) 이 붙여져 있는 것들을 다 추출해라
         */


    }

    private RequestMethod[] getRequestMethod(RequestMapping requestMapping) {
        return requestMapping.method(); // get 인지 post 구분해주기 위해
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {

        return handlers.get(handlerKey);
    }
}
