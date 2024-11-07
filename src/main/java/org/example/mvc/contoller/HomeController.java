package org.example.mvc.contoller;

import org.example.mvc.annotaion.Controller;
import org.example.mvc.annotaion.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hadnlerRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home";
    }
}
