package org.example.mvc.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {  // viewname 을 처리하기위한 클래스( home, list, users, 등 )

    private Object view;
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView(String viewName) {
        this.view = viewName;
    }

    public Map<String, Object> getModel() {
        return Collections.unmodifiableMap(model);
        /*
            Collections.unmodifiableMap
            수정 불가능한 보기를 반환하는 데 사용됩니다. 이 방법을 사용하면 모듈이 사용자에게 내부 맵에 대한 "읽기 전용" 액세스 권한을 제공할 수 있습니다.
         */
    }

    public String getViewName() {
        return (this.view instanceof String ? (String) this.view : null); // 삼항연산자
    }
}
