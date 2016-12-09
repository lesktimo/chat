package wepa.app.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wepa.app.domain.Account;
import wepa.app.repo.AccountRepo;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getAccount(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountRepo.findOne(id));

        return null;
    }

    @PostConstruct
    public void init() {
        if (accountRepo.findByUsername("jukka") == null) {
            Account account = new Account();
            account.setUsername("jukka");
            account.setPassword("salajukka");
            accountRepo.save(account);
        }
    }
}
