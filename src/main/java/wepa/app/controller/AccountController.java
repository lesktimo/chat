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
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountService accService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAccount(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountRepo.findOne(id));

        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("accountForm", new Account());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("accountForm") Account accountForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        accService.save(accountForm);

        return "redirect:/login";
    }
}
