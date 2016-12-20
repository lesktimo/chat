package wepa.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String handleDefault() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().isEmpty() || auth.getName() == null) {

            return "redirect:/login";

        } else {

            return "redirect:/groups";
        }
    }

}
