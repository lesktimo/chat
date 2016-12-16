package wepa.app.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String handleDefault(final Principal principal) {
        if (principal == null) {
            return "redirect:/reg";
        } else {
            return "redirect:/groups";
        }
    }
}
