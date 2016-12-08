package wepa.app.service;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wepa.app.domain.Account;

@Service
public class AccountService {

    @Autowired
    private HttpSession session;

    public void setAccount(Account account) {
        session.setAttribute("account", account);
    }

    public Account getAccount() {
        return (Account) session.getAttribute("account");
    }
}
