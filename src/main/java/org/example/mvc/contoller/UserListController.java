package org.example.mvc.contoller;

import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Controller{ // 유저의 목록을 리턴하는 클래스
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.setAttribute("users", List.of());
//        return "/user/list.jsp";
        request.setAttribute("users", UserRepository.findAll());
        return "/user/list";
    }

}
