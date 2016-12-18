package wepa.app.service;

import java.util.HashSet;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wepa.app.domain.Account;
import wepa.app.repo.AccountRepo;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accRepo;

    @PostConstruct
    public void init() {
        if (accRepo.findByUsername("testi") == null) {
            HashSet<SimpleGrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority("USER"));
            Account account = new Account();
            account.setUsername("testi");
            account.setPassword(passwordEncoder().encode("testi"));
            account.setRoles(roles);
            accRepo.save(account);
        }
    }

    public void save(Account acc) {
        HashSet<SimpleGrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("USER"));
        acc.setPassword(passwordEncoder().encode(acc.getPassword()));
        acc.setRoles(new HashSet<>(roles));
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
