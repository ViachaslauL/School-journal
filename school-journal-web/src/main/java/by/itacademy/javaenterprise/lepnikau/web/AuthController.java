package by.itacademy.javaenterprise.lepnikau.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "index";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String getSuccessPage() {
        return "success";
    }
}
