package wepa.app.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wepa.app.domain.Account;
import wepa.app.repo.AccountRepo;
import wepa.app.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountService accService;
    
    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String getAccount(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountRepo.findOne(id));

        return null;
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String registrationGet(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registrationPost(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        accService.save(account);
        return "redirect:/login";
    }
}
