package org.example.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class RedirectView implements View{  // JspViewResolver에서 받은게 redirect 일때 처리해주는 클래스
    public static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    private final String name;

    public RedirectView(String name) {
        this.name = name;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(name.substring(DEFAULT_REDIRECT_PREFIX.length())); // redirect 이후의 값을 substring 해라
        // sendRedirect 가 있어서 redirect 가 자동으로 붙여줌
    }
}
