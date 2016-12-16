
package wepa.app.service;

import java.util.HashSet;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wepa.app.domain.Account;
import wepa.app.repo.AccountRepo;
import wepa.app.repo.RoleRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accRepo;

    @Autowired
    private RoleRepo roleRepo;
    
    @PostConstruct
    public void init(){
        if(accRepo.findByUsername("testi")==null){
            Account account = new Account();
            account.setUsername("testi");
            account.setPassword(passwordEncoder().encode("testi"));
            account.setRoles(new HashSet<>(roleRepo.findAll()));
            accRepo.save(account);
        }
    }
    
    public void save(Account acc) {
        acc.setPassword(passwordEncoder().encode(acc.getPassword()));
        acc.setRoles(new HashSet<>(roleRepo.findAll()));
        accRepo.save(acc);
    }

    public Account findByUsername(String username) {
        return accRepo.findByUsername(username);
    }

     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
