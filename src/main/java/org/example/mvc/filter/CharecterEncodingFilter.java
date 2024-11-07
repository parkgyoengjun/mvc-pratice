package org.example.mvc.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/") // 모든 경로에 필터 적용
public class CharecterEncodingFilter implements Filter {
    public static final String DEFAILT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(DEFAILT_ENCODING);
        response.setCharacterEncoding(DEFAILT_ENCODING);

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
