package org.example.mvc.contoller;

import org.example.mvc.model.User;
import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // user 추가
        UserRepository.save(new User(request.getParameter("userId"),request.getParameter("name")));
        return "redirect:/users"; // 저장만 하고 바로 가니까 redirect
        // 클라이언트쪽으로 users라는 경로를 가진쪽으로 요청(GET 요청) -> UserListCreateController 로 간다.
    }
}
