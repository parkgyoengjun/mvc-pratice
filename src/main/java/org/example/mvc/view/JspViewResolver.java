package org.example.mvc.view;

import static org.example.mvc.view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JspViewResolver implements ViewResolver { // jsp 인지 redirect 인지 구분해주는 클래스
    @Override
    public View resolveView(String viewName) {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {  // return 받은게 redirect: 로 시작하는지 아닌지
            // viewName.startwith("a") -> viewName 의 문자열이 "a" 로 시작하는지 묻는거
            return new RedirectView(viewName); // redirect 형태면 redirectview 형태로
        }
        else return new JspView(viewName + " .jsp"); // jsp 형태면 jspview 형태로
    }
}
